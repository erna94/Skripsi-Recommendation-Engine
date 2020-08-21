package service.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.ecommerce.entities.Product;

@RestController
@RequestMapping("/api/produk/rekomendasi")
public class RekomendasiProdukController {
	
	@GetMapping("/{userid}")
    public List<Product>  getRekomendasiProduk(@PathVariable Long userid) {
		List<Product> rekomendasiProduk = new ArrayList<Product>();
		
		
		 
		// panggil step KNN, purchase history dan ranking
        return rekomendasiProduk;
    }
}
