package service.ecommerce.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

/***
 * memberitahu kalau kelas ini berhubungan dengan SQL Table riwayat_pembelian
 * id_user int not null PRIMARY KEY AUTO_INCREMENT, 
 * id_produk int not null, 
 * jumlah int not null, 
 * tanggal_beli date, 
 *
 */
@Table(name="riwayat_pembelian")
public class PurchaseHistory {
	
	@Id // @ID ini untuk menandakan kalau column id_user itu adalah primary keynya 
	// @Column buat kasih tau kalau ini idUser 
	// di object di hubungkan ke kolom id_user di database
	@Column(name="id_user", unique = true) 
	Long idUser;
	
	@Column(name="id_produk", unique = true) 
	Long idProduk;
	
	@Column(name="jumlah")
	int jumlah;
	
	@Column(name="tanggal_beli")
	Date tanggalBeli;
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdProduk() {
		return idProduk;
	}

	public void setIdProduk(Long idProduk) {
		this.idProduk = idProduk;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public Date getTanggalBeli() {
		return tanggalBeli;
	}

	public void setTanggalBeli(Date tanggalBeli) {
		this.tanggalBeli = tanggalBeli;
	}
}
