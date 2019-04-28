package service.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produk/rekomendasi")
public class RekomendasiProdukController {
	
	
	@GetMapping("/{userid}")
    public Long getRekomendasiProduk(@PathVariable Long userid) {
        return userid;
    }
	
	@GetMapping("/category/{id}")
    public Long getProdukBerdasarkanCategory(@PathVariable Long id) {
        return id;
    }
}
