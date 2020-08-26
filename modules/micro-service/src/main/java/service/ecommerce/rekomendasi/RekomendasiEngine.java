package service.ecommerce.rekomendasi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
		
		// buat debug saja
		Iterator<Double> keys = jarakDenganUserLain.keySet().iterator();
		while(keys.hasNext()) {
			double jarak = keys.next();
			List<User> users = jarakDenganUserLain.get(jarak);
			for(int i=0;i<users.size();i++) {
				System.out.println((i+1) + ". Jarak " + jarak + " untuk " + users.get(i));
			}
		}
		
		userYangMirip = jalankanIterasiKNN(10,jarakDenganUserLain);
		
		return userYangMirip;
	}
	
	public List<User> jalankanIterasiKNN(int k, Map<Double, ArrayList<User>> listUserDenganJarak) {
		List<User> userYangMirip = new ArrayList<User>();
		
		Iterator<ArrayList<User>> jumlahUserDenganJarak = listUserDenganJarak.values().iterator();
		
		while(userYangMirip.size() <= k && jumlahUserDenganJarak.hasNext()) {
			ArrayList<User> userDalamData = jumlahUserDenganJarak.next();		
			for(int j=0;j<userDalamData.size() & userYangMirip.size() <= k;j++) {
				User userMiripUntukDitambah = userDalamData.get(j);
				userYangMirip.add(userMiripUntukDitambah);
			}
		}
		
		return userYangMirip;
	}
	
	public double hitungJarak(User user2, User user1) {
		// hitung semua attribute user dan memberikan jarak	
		
		//0.0 artinya lokasinya sama, kalo 1.0 berati lokasinya berbeda.
		double bedaLokasi = 1.0;
		if(! user2.getLokasi().equals(user1.getLokasi()));
		double bedaLokasiPangkat2 = Math.pow(2, bedaLokasi);
		
		double bedaKerjaan = 1.0;
		if(! user2.getPekerjaan().equals(user1.getPekerjaan()));
		double bedaKerjaanPangkat2 = Math.pow(2, bedaKerjaan);
		
		double bedaUmur = user2.getZScoreUmur() - user1.getZScoreUmur();
		double bedaUmurPangkat2 = Math.pow(2, bedaUmur);
		
		double tambahPangkat = bedaLokasiPangkat2 + bedaKerjaanPangkat2 + bedaUmurPangkat2;
		double akarSemua = Math.sqrt(tambahPangkat);
		
		return akarSemua;
	}
	

	
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
		List<Product> rekomendasiProduk = this.productRepository.findProductByPurchaseHistory(idUserYangMirip);
		
		return rekomendasiProduk;
	}
	
	
	/**
	 * Hanya untuk testing, bukan bagian dari kode
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Running application");
	}
}
