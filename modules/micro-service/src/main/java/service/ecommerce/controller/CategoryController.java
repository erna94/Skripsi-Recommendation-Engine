package service.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.db.CategoryRepository;
import service.ecommerce.entities.Category;

@RestController
@RequestMapping("/api/category/children")
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	private List<Category> getProductByIdCategoryFromDB(Long categoryId) {
		// code buat panggil database
		List<Category> categoryList = categoryRepository.findChildCategory(categoryId);
		return categoryList;
	}
}