package service.ecommerce.entities;

public class FrequencyStats {
	private Product produk;
	private Long frekuensi;
	
	public Product getProduk() {
		return produk;
	}
	
	public void setProduk(Product produk) {
		this.produk = produk;
	}
	
	public Long getFrekuensi() {
		return frekuensi;
	}
	
	public void setFrekuensi(Long frekuensi) {
		this.frekuensi = frekuensi;
	}
}
