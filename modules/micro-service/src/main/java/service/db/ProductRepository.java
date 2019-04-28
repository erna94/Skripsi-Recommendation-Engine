package service.db;

import org.springframework.data.repository.CrudRepository;

import service.ecommerce.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
	/***
	 * Mencari satu product dengan suatu product ID tertentu
	 * @param id
	 * @return
	 */
	Product findByIdProduct(Long idProduct);
}
