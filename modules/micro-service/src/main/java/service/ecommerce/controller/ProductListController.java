package service.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.db.ProductRepository;
import service.ecommerce.entities.Product;

@RestController
@RequestMapping("/api/produk/list")
public class ProductListController {
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/{categoryId}")
	public List<Product> getProductByCategory(@PathVariable Long categoryId) {
		// panggil database buat dapetin produk berdasarkan category yang di kasih
		List<Product> p = new ArrayList<Product>();
		
		// ini sudah category paling bawah kalau lebih dari 100
		if(categoryId > 100) {
			p = productRepository.findProductByCategory(categoryId);
		} else if (categoryId > 10 && categoryId < 100) {
			p = productRepository.findProductByParentCategory(categoryId);
		}
		
        return p;   
    }
}
