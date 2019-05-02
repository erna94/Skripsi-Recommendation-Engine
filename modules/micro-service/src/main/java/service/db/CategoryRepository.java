package service.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import service.ecommerce.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{
	/***
	 * Mencari satu product dengan suatu product ID tertentu
	 * @param id
	 * @return
	 */
	Category findByIdCategory(Long idCategory);
	
	@Query("SELECT p FROM Category p WHERE p.parentId IN (SELECT p.idCategory FROM Category p WHERE p.parentId = :categoryId)")
	// membuat query untuk mencari product berdasarkan category, di dalam query
	// ini kita memberitahukan kepada JPA kalau untuk hasilnya akan menggunakan
	// object Product dan secara otomatis hasil dari SQL kolomnya akan di hubungkan
	// dengan menggunakan @Column di dalam object Product
	List<Category> findProductByIdCategory(@Param(value = "categoryId") Long categoryId);
}