package service.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

/***
 * memberitahu kalau kelas ini berhubungan dengan SQL Table Product
 * id_product int not null PRIMARY KEY AUTO_INCREMENT, 
 * id_category int not null, 
 * nama_product varchar(225) not null, 
 * deskripsi_product text, 
 * harga_product decimal not null,  
 * id_penjual int not null, 
 * image_link varchar(225) not null,
 *
 */
@Table(name="product")
public class Product {
	
	@Id
	@Column(name="id_product", unique = true)
	Long idProduct;
	
	@Column(name="id_category")
	int idCategory;
	
	@Column(name="nama_product")
	String namaProduct;
	
	@Column(name="deskripsi_product")
	String deskripsiProduct;
	
	@Column(name="harga_product")
	int hargaProduct;
	
	@Column(name="id_penjual")
	int idPenjual;
	
	@Column(name="image_link")
	String imageLink;
	
	public Long getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(Long idProduct) {
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
