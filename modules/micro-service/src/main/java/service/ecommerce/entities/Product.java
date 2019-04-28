package service.ecommerce.entities;

public class Product {
	int idProduct;
	int idCategory;
	String namaProduct;
	String deskripsiProduct;
	int hargaProduct;
	int idPenjual;
	String imageLink;
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public String getNamaProduct() {
		return namaProduct;
	}
	public void setNamaProduct(String namaProduct) {
		this.namaProduct = namaProduct;
	}
	public String getDeskripsiProduct() {
		return deskripsiProduct;
	}
	public void setDeskripsiProduct(String deskripsiProduct) {
		this.deskripsiProduct = deskripsiProduct;
	}
	public int getHargaProduct() {
		return hargaProduct;
	}
	public void setHargaProduct(int hargaProduct) {
		this.hargaProduct = hargaProduct;
	}
	public int getIdPenjual() {
		return idPenjual;
	}
	public void setIdPenjual(int idPenjual) {
		this.idPenjual = idPenjual;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	
	

}
