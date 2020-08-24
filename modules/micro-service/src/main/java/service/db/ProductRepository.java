package service.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import service.ecommerce.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	/***
	 * Mencari satu product dengan suatu product ID tertentu
	 * @param id
	 * @return
	 */
	Product findByIdProduk(Long idProduk);
	
	@Query("SELECT p FROM Product p WHERE p.idKategori = :kategoriId")
	// membuat query untuk mencari product berdasarkan category, di dalam query
	// ini kita memberitahukan kepada JPA kalau untuk hasilnya akan menggunakan
	// object Product dan secara otomatis hasil dari SQL kolomnya akan di hubungkan
	// dengan menggunakan @Column di dalam object Product
	List<Product> findProductByCategory(@Param(value = "kategoriId") Long kategoriId);
	
	
	@Query("SELECT p FROM Product p where p.idKategori IN  (SELECT c.idKategori  FROM Category c WHERE c.parentId = :kategoriId)")
	List<Product> findProductByParentCategory(@Param(value = "kategoriId") Long kategoriId);
	
	
	 @Query("SELECT p FROM Product p JOIN PurchaseHistory ph ON p.idProduk = ph.idProduk WHERE ph.idUser IN (:ids) ")     // 2. Spring JPA In cause using @Query
	 List<Product> findProductByPurchaseHistory(@Param("ids")List<Long> ids);
}
