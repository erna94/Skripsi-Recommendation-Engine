package service.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

/***
 * memberitahu kalau kelas ini berhubungan dengan SQL Table user
 * id_user int not null PRIMARY KEY AUTO_INCREMENT, 
 * lokasi varchar(225) not null, 
 * pekerjaan varchar(225) not null, 
 * umur int not null, 
 *
 */
@Table(name="users")
public class User {
	
	@Id // @ID ini untuk menandakan kalau column id_product itu adalah primary keynya 
	// @Column buat kasih tau kalau ini idProduct 
	// di object di hubungkan ke kolom id_product di database
	@Column(name="id_user", unique = true) 
	Long idUser;
	
	@Column(name="lokasi")
	String lokasi;
	
	@Column(name="pekerjaan")
	String pekerjaan;
	
	@Column(name="umur")
	int umur;
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
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
