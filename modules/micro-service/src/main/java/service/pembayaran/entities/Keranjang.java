package service.pembayaran.entities;

import java.util.ArrayList;
import java.util.UUID;

public class Keranjang {
	String keranjangId;
	ArrayList<Product> isiProduct;
	
	public Keranjang() {
		keranjangId = UUID.randomUUID().toString();
		isiProduct = new ArrayList<Product>();
	}

	public String getKeranjangId() {
		return keranjangId;
	}

	public void setKeranjangId(String keranjangId) {
		this.keranjangId = keranjangId;
	}
	
	

	public ArrayList<Product> getIsiProduct() {
		return isiProduct;
	}

	public void setIsiProduct(ArrayList<Product> isiProduct) {
		this.isiProduct = isiProduct;
	}

	/***
	 * Method buat menambahkan product
	 * @param produkBuatDitambahkan
	 */
	public void addProduct(Product produkBuatDitambahkan) {
		isiProduct.add(produkBuatDitambahkan);
	}
}
