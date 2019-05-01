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
	
	@Id // @ID ini untuk menandakan kalau column id_product itu adalah primary keynya 
	// @Column buat kasih tau kalau ini idProduct 
	// di object di hubungkan ke kolom id_product di database
	@Column(name="id_product", unique = true) 
	Long idProduct;
	
	@Column(name="id_category")
	Long idCategory;
	
	@Column(name="nama_product")
	String namaProduct;
	
	@Column(name="deskripsi_product")
	String deskripsiProduct;
	
	@Column(name="harga_product")
	double hargaProduct;
	
	@Column(name="id_penjual")
	Long idPenjual;
	
	@Column(name="image_link")
	String imageLink;

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
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

	public double getHargaProduct() {
		return hargaProduct;
	}

	public void setHargaProduct(double hargaProduct) {
		this.hargaProduct = hargaProduct;
	}

	public Long getIdPenjual() {
		return idPenjual;
	}

	public void setIdPenjual(Long idPenjual) {
		this.idPenjual = idPenjual;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	
}
