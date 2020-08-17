package service.ecommerce.rekomendasi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.ecommerce.entities.Login;
import service.ecommerce.entities.User;
import java.lang.Math;

public class RekomendasiEngine {
	/***
	 * Menggunakan algoritma untuk mencari user yang mirip dengan userUntukDianalisa
	 * @param userUntukDianalisa
	 * @return
	 */
	public List<User> cariUserYangMirip(User userUntukDianalisa, List<User> semuaUser) {
		List<User> userYangMirip = new ArrayList<User>();
		
		// Map itu kaya table dua kolom, di kolom pertama adalah Usernya yang dibandingkan, di kolom kedua
		// kita mengisikan jarak antara userUntukDianalisa dengan user yang dibandingkan
		Map<User, Double> jarakDenganUserLain = new HashMap<User, Double>();
		
		for(int i=0;i<semuaUser.size();i++) {
			User user2 = semuaUser.get(i);
			double jarakUser = hitungJarak(userUntukDianalisa, user2);
			jarakDenganUserLain.put(user2, jarakUser);
		}
		
		return userYangMirip;
	}
	
	public double hitungJarak(User user2, User user1) {
		// hitung semua attribute user dan memberikan jarak	
		
		//0.0 artinya lokasi kita sama, kalo 1.0 berati lokasi kita berbeda.
		double bedaLokasi = 1.0;
		if(! user2.getLokasi().equals(user1.getLokasi()));
		double bedaLokasiPangkat2 = Math.pow(2, bedaLokasi);
		
		double bedaKerjaan = 1.0;
		if(! user2.getPekerjaan().equals(user1.getPekerjaan()));
		double bedaKerjaanPangkat2 = Math.pow(2, bedaKerjaan);
		
		int bedaUmur = user2.getUmur() - user1.getUmur();
		double bedaUmurPangkat2 = Math.pow(2, bedaUmur);
		
		double tambahPangkat = bedaLokasiPangkat2 + bedaKerjaanPangkat2 + bedaUmurPangkat2;
		double akarSemua = Math.sqrt(tambahPangkat);
		
		return akarSemua;
	
	}
}
