package service.ecommerce.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Keranjang {
	String keranjangId;
	Map<Product, Integer> isiProduct;

	public Keranjang() {
		keranjangId = UUID.randomUUID().toString();
		isiProduct = new HashMap<Product, Integer>();
	}

	public String getKeranjangId() {
		return keranjangId;
	}

	public void setKeranjangId(String keranjangId) {
		this.keranjangId = keranjangId;
	}

	public Map<Product, Integer> getIsiProduct() {
		return isiProduct;
	}

	public void setIsiProduct(Map<Product, Integer> isiProduct) {
		this.isiProduct = isiProduct;
	}

	/***
	 * Method buat menambahkan product
	 * @param produkBuatDitambahkan
	 */
	public void addProduct(Product produkBuatDitambahkan, int jumlahPenambahan) {
		Long id = produkBuatDitambahkan.getIdProduct();
		// update isi produk dengan jumlahnya
		isiProduct.put(produkBuatDitambahkan, jumlahPenambahan);			
	}

	/***
	 * Method buat menambahkan product
	 * @param produkBuatDitambahkan
	 */
	public void subtractProduct(Product produkBuatDitambahkan) {
		Long id = produkBuatDitambahkan.getIdProduct();

		int jumlahProduk = isiProduct.get(id) - 1;
		
		if(jumlahProduk == 0) {
			isiProduct.remove(id);			
		} else {
			isiProduct.put(produkBuatDitambahkan, jumlahProduk);			
		}
	}
}
