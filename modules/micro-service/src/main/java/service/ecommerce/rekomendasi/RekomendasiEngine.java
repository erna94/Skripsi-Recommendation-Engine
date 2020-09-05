package service.ecommerce.rekomendasi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import service.db.ProductRepository;
import service.db.UserRepository;
import service.ecommerce.entities.Product;
import service.ecommerce.entities.User;

public class RekomendasiEngine {
	UserRepository userRepository;
	ProductRepository productRepository;
	
	// library buat menghitung statistics
	DescriptiveStatistics statEngine;
	
	public RekomendasiEngine(UserRepository userRepository, ProductRepository productRepository) {
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.statEngine = new DescriptiveStatistics();
		
	}
	
	/***
	 * Menggunakan algoritma untuk mencari user yang mirip dengan userUntukDianalisa
	 * @param userUntukDianalisa
	 * @return
	 */
	public List<User> cariUserYangMirip(User userUntukDianalisa, List<User> semuaUser) {
		
		System.out.println("Mencari user yang mirip dengan umur " + userUntukDianalisa.getUmur() 
					+ " - pekerjaan " + userUntukDianalisa.getPekerjaan() 
					+ " dan lokasi " + userUntukDianalisa.getLokasi()  + "\n");
		
		List<User> userYangMirip = new ArrayList<User>();
		
		// Map itu kaya table dua kolom, di kolom pertama adalah perbedaan jaraknya
		// dan di kolom ke dua, semua user yang mempunyai jarak tersebut ketika
		// dibandingkan dengan userUntukDianalasisa
		Map<Double, ArrayList<User>> jarakDenganUserLain = 
				new TreeMap<Double, ArrayList<User>>();
		
		
		// hitung data statistics untuk rata2 dan standard deviasi
		for(int i=0;i<semuaUser.size();i++) {
			User user = semuaUser.get(i); 
			statEngine.addValue((double)user.getUmur());
		}
		
		double rata2 = statEngine.getMean();
		double std = statEngine.getStandardDeviation();
		
		userUntukDianalisa.setAverage(rata2);
		userUntukDianalisa.setStandardDeviation(std);
		
		for(int i=0;i<semuaUser.size();i++) {
			ArrayList<User> listUserUntukDitampung = new ArrayList<User>();
			
			User user2 = semuaUser.get(i); 
			user2.setAverage(rata2);
			user2.setStandardDeviation(std);
			
			double jarakUser = hitungJarak(userUntukDianalisa, user2);
			
			
			// Kalau sudah pernah ada user dengan jarak yang sama, kita ambil 
			// list tersebut dan menambahkan usernya. If statement ini hanya
			// terjadi kalau sudah ada user dengan jarak yang sama
			if(jarakDenganUserLain.containsKey(jarakUser)) {
				listUserUntukDitampung = jarakDenganUserLain.get(jarakUser);
			} 
			
			listUserUntukDitampung.add(user2);
			jarakDenganUserLain.put(jarakUser, listUserUntukDitampung);
		}
		
		System.out.println("User yang mirip dengan " + userUntukDianalisa);
		
		userYangMirip = jalankanIterasiKNN(10,jarakDenganUserLain);
		
		return userYangMirip;
	}
	
	public List<User> jalankanIterasiKNN(int k, Map<Double, ArrayList<User>> listUserDenganJarak) {
		List<User> userYangMirip = new ArrayList<User>();
		
		// buat debug saja
		Iterator<Double> keys = listUserDenganJarak.keySet().iterator();
		while(userYangMirip.size() <= k && keys.hasNext()) {
			double jarak = keys.next();
			List<User> users = listUserDenganJarak.get(jarak);
			for(int i=0;i<users.size();i++) {
				User userMiripUntukDitambah = users.get(i);
				System.out.println((i+1) + ". Jarak " + jarak + " untuk " + userMiripUntukDitambah);
				userYangMirip.add(userMiripUntukDitambah);
			}
		}
		
		
		return userYangMirip;
	}
	
	public double hitungJarak(User user2, User user1) {
		// hitung semua attribute user dan memberikan jarak	
		
		//0.0 artinya lokasinya sama, kalo 1.0 berati lokasinya berbeda.
		double bedaLokasi = 0.0;
		if(! user2.getLokasi().equals(user1.getLokasi())) {
			bedaLokasi = 1.0;
		}
		
		double bedaLokasiPangkat2 = Math.pow(2, bedaLokasi);
		
		double bedaKerjaan = 0.0;
		if(! user2.getPekerjaan().equals(user1.getPekerjaan())) {
			bedaKerjaan = 1.0;
		}
		
		double bedaKerjaanPangkat2 = Math.pow(2, bedaKerjaan);
		
		double bedaUmur = user2.getZScoreUmur() - user1.getZScoreUmur();
		double bedaUmurPangkat2 = Math.pow(2, bedaUmur);
		
		double tambahPangkat = bedaLokasiPangkat2 + bedaKerjaanPangkat2 + bedaUmurPangkat2;
		double akarSemua = Math.sqrt(tambahPangkat);
		
		return akarSemua;
	}
	

	/***
	 * Mencari hasil rekomendasi berdasarkan langkah2 berikut
	 * 1. Cari user yang mirip
	 * 2. Untuk semua user yang mirip cari riwayat pembelian (Purchase History)
	 * 3. Untuk semua purchase history di cari frequensi yang paling sering di beli lalu rekomendasi di urutkan berdasarkan itu
	 * @param userUntukDicarikanRekomendasi
	 * @return
	 */
	public List<Product> cariRekomendasi(User userUntukDicarikanRekomendasi) {
		
		List<User> semuaUser = userRepository.findSemuaUsers();		
		List<User> userYangMirip = cariUserYangMirip(userUntukDicarikanRekomendasi, semuaUser);
		
		ArrayList<Long> idUserYangMirip = new ArrayList<Long>();
		for(int i=0;i<userYangMirip.size();i++) {
			User userDiList = userYangMirip.get(i);
			Long idUser = userDiList.getIdUser();
			idUserYangMirip.add(idUser);
		}
		
		// Ambil semua purchase history yang mirip untuk list of user
		List<Product> hasilPurchaseHistory = this.productRepository.findProductByPurchaseHistory(idUserYangMirip);
		
		System.out.println("\n\nMenemukan purchase History dengan jumlah " + hasilPurchaseHistory.size());
		
		// Ini TreeMap adalah tabel dua kolom yang kolom pertama adalah IDnya, kolom ke dua adalah
		// produk2 yang sesuai dengan ID tersebut
		Map<Long, ArrayList<Product>> produkDenganFrequency = new TreeMap<Long, ArrayList<Product>>();
		
		// cari frequensi tiap produk skrg, pertama2 itung frequency tiap productID
		for(int i=0;i<hasilPurchaseHistory.size();i++) {
			Product produkDiAnalisa = hasilPurchaseHistory.get(i);
			Long idProduk = produkDiAnalisa.getIdProduk();
			
			
			ArrayList<Product> produkYangSama = new ArrayList<Product>();
			
			if(produkDenganFrequency.containsKey(idProduk)) {
				// kalau sudah pernah disimpan listnya, ambil yang dulu ada 
				// trus di ganti isi variablenya dengan yang ada di dalam tabel
				produkYangSama = produkDenganFrequency.get(idProduk);
			}
			
			// masukin is produk yang akan di analisa ke dalam listnya
			produkYangSama.add(produkDiAnalisa);
			produkDenganFrequency.put(idProduk, produkYangSama);
		}
		
		System.out.println("Setelah mencari mendapatkan purchase history unik sebanyak " + produkDenganFrequency.keySet().size());
		
		
		// pada akhir iterasi dari baris 159-172, kita mendapatkan hasil id dengan list produk
		// contoh kalo dalam purchase history ada dua sandal dengan id 2 dan tiga baju dengan id 5, 
		// kita akan punya isi tabel Map
		// sebagai berikut:
		/*
		id      |       barang
		2		|		sandal, sandal		
		5		|		baju, baju, baju		
		*/
		
		// Setelah mendapatkan hasil frekuensi, kita akkan mengurutkan mana yang paling banyak
		// dan di berikan ke hasil rekomendasinya
		// treeMap mengorder dari kecil ke besar, tapi kita mau sebaliknya jadi harus menggunakan reverseOrder
		
		Map<Integer, ArrayList<ArrayList<Product>>> hasilUrutanFrequency = 
				new TreeMap<Integer, ArrayList<ArrayList<Product>>>(Collections.reverseOrder());
		
		Collection<ArrayList<Product>> hasilPerhitunganFrequency = produkDenganFrequency.values(); 
		
		for(ArrayList<Product> current : hasilPerhitunganFrequency) {
			Integer jumlahFrequency = current.size();
			

			ArrayList<ArrayList<Product>> koleksiProdukDenganFrequensiSama = new ArrayList<ArrayList<Product>>();
			
			if(hasilUrutanFrequency.containsKey(jumlahFrequency)) {
				koleksiProdukDenganFrequensiSama = hasilUrutanFrequency.get(jumlahFrequency);
			}
	
			koleksiProdukDenganFrequensiSama.add(current);

			hasilUrutanFrequency.put(jumlahFrequency, koleksiProdukDenganFrequensiSama);
		}
		
		// skrg kita sudah mengurutkan dengan menggunakan TreeMap, kita akan menaruh mulai
		// dari frequency paling besar ke paling kecil
		
		Collection< ArrayList<ArrayList<Product>>> hasilAkhir = hasilUrutanFrequency.values(); 
		
		System.out.println("\nANALISA FREKUENSI " + hasilPurchaseHistory.size());
		int i = 1;
		List<Product>  hasilAkhirRekomendasi = new ArrayList<Product>();
		for(ArrayList<ArrayList<Product>> hasilUrutan : hasilAkhir) {
			for(ArrayList<Product> current: hasilUrutan) {		
				// kita cuma perlu mendapatkan produk pertama
				Product pertama = current.get(0);
				String namaProduk = pertama.getNamaProduk();
				Long idProduk = pertama.getIdProduk();
				Integer frequency = current.size();
				
				System.out.println(i++ + ". " + namaProduk + "- Produk " + idProduk + " dengan frequency " + frequency);
				hasilAkhirRekomendasi.add(pertama);
			}
		}
		
		return hasilAkhirRekomendasi;
	}
}
