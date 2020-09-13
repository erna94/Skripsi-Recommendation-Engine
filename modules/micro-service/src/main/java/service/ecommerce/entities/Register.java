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
@Table(name="user")
public class Register {

	@Id // @ID ini untuk menandakan kalau column id_user itu adalah primary keynya 
	// @Column buat kasih tau kalau ini idUser 
	// di object di hubungkan ke kolom id_user di database
	@Column(name="id_user", unique = true) 
	Long idUser;
	
	@Column(name="username", unique = true) 
	String userName;
	
	@Column(name="email", unique = true) 
	String email;
	
	@Column(name="password", unique = true) 
	String password;
	
	@Column(name="umur")
	int umur;
	
	@Column(name="lokasi")
	String lokasi;
	
	@Column(name="pekerjaan")
	String pekerjaan;
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUmur() {
		return umur;
	}

	public void setUmur(int umur) {
		this.umur = umur;
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

}
