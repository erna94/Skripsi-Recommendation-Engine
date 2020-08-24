package service.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="kategori")
public class Category {
	
	@Id // @ID ini untuk menandakan kalau column id_product itu adalah primary keynya 
	// @Column buat kasih tau kalau ini idCategory 
	// di object di hubungkan ke kolom id_category di database
	@Column(name="id_kategori", unique = true) 
	Long idKategori;
	
	@Column(name="nama_kategori")
	String namaKategori;
	
	@Column(name="parent_id")
	Long parentId;

	public Long getIdKategori() {
		return idKategori;
	}

	public void setIdKategori(Long idKategori) {
		this.idKategori = idKategori;
	}

	public String getNamaKategori() {
		return namaKategori;
	}

	public void setNamaKategori(String namaKategori) {
		this.namaKategori = namaKategori;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
