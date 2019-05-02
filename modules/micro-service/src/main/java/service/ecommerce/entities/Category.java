package service.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name="category")
public class Category {


	@Id // @ID ini untuk menandakan kalau column id_product itu adalah primary keynya 
	// @Column buat kasih tau kalau ini idProduct 
	// di object di hubungkan ke kolom id_product di database
	@Column(name="id_category", unique = true) 
	Long idCategory;
	
	@Column(name="nama_category")
	String namaCategory;
	
	@Column(name="parent_id")
	Long parentId;

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public String getNamaCategory() {
		return namaCategory;
	}

	public void setNamaCategory(String namaCategory) {
		this.namaCategory = namaCategory;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
}
