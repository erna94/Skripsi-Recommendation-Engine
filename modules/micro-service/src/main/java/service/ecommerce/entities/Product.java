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
@Table(name="produk")
public class Product {
	
	@Id // @ID ini untuk menandakan kalau column id_product itu adalah primary keynya 
	// @Column buat kasih tau kalau ini idProduct 
	// di object di hubungkan ke kolom id_product di database
	@Column(name="id_produk", unique = true) 
	Long idProduk;
	
	@Column(name="id_kategori")
	Long idKategori;
	
	@Column(name="nama_produk")
	String namaProduk;
	
	@Column(name="deskripsi_produk")
	String deskripsiProduk;
	
	@Column(name="harga_produk")
	double hargaProduk;
	
	@Column(name="image_link")
	String imageLink;
	
	@Column(name="brand")
	String brand;
	
	@Column(name="warna")
	String warna;
	
	@Column(name="bahan")
	String bahan;
	
	public Long getIdProduk() {
		return idProduk;
	}

	public void setIdProduk(Long idProduk) {
		this.idProduk = idProduk;
	}

	public Long getIdKategori() {
		return idKategori;
	}

	public void setIdKategori(Long idKategori) {
		this.idKategori = idKategori;
	}

	public String getNamaProduk() {
		return namaProduk;
	}

	public void setNamaProduk(String namaProduk) {
		this.namaProduk = namaProduk;
	}

	public String getDeskripsiProduk() {
		return deskripsiProduk;
	}

	public void setDeskripsiProduk(String deskripsiProduk) {
		this.deskripsiProduk = deskripsiProduk;
	}

	public double getHargaProduk() {
		return hargaProduk;
	}

	public void setHargaProduk(double hargaProduk) {
		this.hargaProduk = hargaProduk;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getWarna() {
		return warna;
	}

	public void setWarna(String warna) {
		this.warna = warna;
	}

	public String getBahan() {
		return bahan;
	}

	public void setBahan(String bahan) {
		this.bahan = bahan;
	}
}
