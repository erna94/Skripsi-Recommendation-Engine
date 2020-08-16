package service.ecommerce.rekomendasi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.ecommerce.entities.User;

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
			User userUntukDibandingkan = semuaUser.get(i);
			double jarakUser = hitungJarak(userUntukDianalisa, userUntukDibandingkan);
			jarakDenganUserLain.put(userUntukDibandingkan, jarakUser);
		}
		
		return userYangMirip;
	}
	
	public double hitungJarak(User userUntukDibandingkan, User userYangLain) {
		// hitung semua attribute user dan memberikan jarak
		return 0.0;
	}
}
