package service.ecommerce.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import service.ecommerce.entities.Keranjang;
import service.ecommerce.entities.Product;

@RestController
@RequestMapping("/api/keranjang")
public class KeranjangController {


	@GetMapping("/{id}")
	public Keranjang getKeranjang(@PathVariable Long id) {
		Keranjang k = getKeranjangDariDB(id);
		return k;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Keranjang create() {
		Keranjang k = new Keranjang();
		return k;
	}
	
	@PutMapping("/{id}")
    public Keranjang updateKeranjang(@RequestBody Map<Product, Integer> productPerubahan, @PathVariable Long id) {
		Keranjang k = getKeranjangDariDB(id);
		k.getIsiProduct().putAll(productPerubahan);
		return k;
    }
	
	@DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
		// Hilangkan keranjang dengan id di parameter dari Database
    }
 

	private Keranjang getKeranjangDariDB(Long id) {
		// code buat panggil database
		Product p = new Product();
		p.setDeskripsiProduct("Ini deskripsi");
		p.setHargaProduct(500);
		p.setIdProduct(1l);
		p.setNamaProduct("Product Erna");

		Keranjang k = new Keranjang();
	
		return k;
	}
}
