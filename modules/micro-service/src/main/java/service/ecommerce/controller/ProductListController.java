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
	
	@GetMapping("/{kategoriId}")
	public List<Product> getProductByCategory(@PathVariable Long kategoriId) {
		// panggil database buat dapetin produk berdasarkan category yang di kasih
		List<Product> p = new ArrayList<Product>();
		
		// ini sudah category paling bawah kalau lebih dari 100
		if(kategoriId > 100) {
			p = productRepository.findProductByCategory(kategoriId);
		} else if (kategoriId > 10 && kategoriId < 100) {
			p = productRepository.findProductByParentCategory(kategoriId);
		}
		
        return p;   
    }
	
	@GetMapping("/purchaseHistory")
	public List<Product> getProductByPurchaseHistory(ArrayList<Long> ids) {
		// panggil database buat dapetin produk berdasarkan category yang di kasih
		List<Product> p = productRepository.findProductByPurchaseHistory(ids);
        return p;   
    }
}
