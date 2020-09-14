package service.ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="login")
public class Login {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	// @ID ini untuk menandakan kalau column id_product itu adalah primary keynya 
	// @Column buat kasih tau kalau ini idProduct 
	// di object di hubungkan ke kolom id_product di database
	@Column(name="id_user", unique = true) 
	Long idUser;

	@Column(name="email")
	String email;
	
	@Column(name="username", nullable = false, unique = true)
	String userName;
	
	@Column(name="password")
	String password;
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

		
}
