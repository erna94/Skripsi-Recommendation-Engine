package service.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.db.ProductRepository;
import service.db.UserRepository;
import service.ecommerce.entities.Product;
import service.ecommerce.entities.User;
import service.ecommerce.rekomendasi.RekomendasiEngine;

@RestController
@RequestMapping("/api/produk/rekomendasi")
public class RekomendasiProdukController {
	@Autowired
	ProductRepository productRepository;
	

	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/{userid}")
    public List<Product>  getRekomendasiProduk(@PathVariable Long userid) {
		RekomendasiEngine engine = new RekomendasiEngine(userRepository, productRepository);
		User userUntukDicari = userRepository.findUserByID(userid);
		List<Product> rekomendasiProduk = engine.cariRekomendasi(userUntukDicari);
		
		// panggil step KNN, purchase history dan ranking
        return rekomendasiProduk;
    }
}
