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
	List<Product> findProductByCategory(@Param(value = "categoryId") Long categoryId);
}
