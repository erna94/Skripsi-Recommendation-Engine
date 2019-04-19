package service.pembayaran.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.pembayaran.entities.Keranjang;
import service.pembayaran.entities.Product;

@RestController
@RequestMapping("/api/keranjang")
public class KeranjangController {
	
	
	@GetMapping("/{id}")
    public Keranjang getKeranjang(@PathVariable Long id) {
		Keranjang k = getKeranjangDariDB(id);
		return k;
    }
	
	private Keranjang getKeranjangDariDB(Long id) {
		// code buat panggil database
		Product p = new Product();
		p.setDeskripsiProduct("Ini deskripsi");
		p.setHargaProduct(500);
		p.setIdProduct(99);
		p.setNamaProduct("Product Erna");
			
		Keranjang k = new Keranjang();
		k.addProduct(p);
	
		return k;
	}
}
