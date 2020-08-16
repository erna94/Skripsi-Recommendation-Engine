package service.ecommerce.entities;

public class User {
	String lokasi;
	String pekerjaan;
	int umur;
	
	public String getLokasi() {
		return lokasi;
	}
	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}
	public String getPekerjaan() {
		return pekerjaan;
	}
	public void setPekerjaan(String pekerjaan) {
		this.pekerjaan = pekerjaan;
	}
	public int getUmur() {
		return umur;
	}
	public void setUmur(int umur) {
		this.umur = umur;
	}
	
}
