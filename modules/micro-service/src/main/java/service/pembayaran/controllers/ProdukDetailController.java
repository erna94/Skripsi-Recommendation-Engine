package service.pembayaran.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produk/detail")
public class ProdukDetailController {
	
	@GetMapping("/{id}")
    public Long getProdukDetail(@PathVariable Long id) {
		
		// panggil database buat dapetin produk berdasarkan id yang di kasih
        return id;
    }
}
