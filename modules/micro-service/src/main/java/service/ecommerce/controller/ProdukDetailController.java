package service.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.ecommerce.entities.Product;

@RestController
@RequestMapping("/api/produk/detail")
public class ProdukDetailController {
	
	@GetMapping("/{id}")
    public Product getProdukDetail(@PathVariable Long id) {
		// panggil database buat dapetin produk berdasarkan id yang di kasih
		Product p = getProductFromDB(id);
        return p;
    }
	
	private Product getProductFromDB(Long id) {
		// code buat panggil database
		Product p = new Product();
		p.setDeskripsiProduct("Ini deskripsi");
		p.setHargaProduct(500);
		p.setIdProduct(99);
		p.setNamaProduct("Product Erna");
		return p;
		
	}
}
