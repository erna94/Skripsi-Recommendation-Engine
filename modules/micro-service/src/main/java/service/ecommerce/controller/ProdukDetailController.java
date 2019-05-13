package service.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.db.ProductRepository;
import service.ecommerce.entities.Category;
import service.ecommerce.entities.Product;

@RestController
@RequestMapping("/api/produk/detail")
public class ProdukDetailController {
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/{id}")
    public Product getProdukDetail(@PathVariable Long id) {
		// panggil database buat dapetin produk berdasarkan id yang di kasih
		Product p = getProductFromDB(id);
        return p;
    }
	
	
	private Product getProductFromDB(Long idProduct) {
		// code buat panggil database
		Product p = productRepository.findByIdProduct(idProduct);
		return p;
	}
}
