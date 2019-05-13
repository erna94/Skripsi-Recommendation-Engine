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
	Product findByIdProduct(Long idProduct);
	
	@Query("SELECT p FROM Product p WHERE p.idCategory = :categoryId")
	// membuat query untuk mencari product berdasarkan category, di dalam query
	// ini kita memberitahukan kepada JPA kalau untuk hasilnya akan menggunakan
	// object Product dan secara otomatis hasil dari SQL kolomnya akan di hubungkan
	// dengan menggunakan @Column di dalam object Product
	List<Product> findProductByCategory(@Param(value = "categoryId") Long categoryId);
	
	
	@Query("SELECT p FROM Product p where p.idCategory IN  (SELECT c.idCategory  FROM Category c WHERE c.parentId = :categoryId)")
	List<Product> findProductByParentCategory(@Param(value = "categoryId") Long categoryId);
}
