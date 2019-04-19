package service.pembayaran.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produk/rekomendasi")
public class RekomendasiProdukController {
	
	
	@GetMapping("/{id}")
    public Long getRekomendasiProduk(@PathVariable Long id) {
        return id;
    }
}
