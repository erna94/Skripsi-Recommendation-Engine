package service.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pembayaran")
public class PembayaranController {
	
	
	@GetMapping("/{id}")
    public Long getTransactions(@PathVariable Long id) {
        return id;
    }
}
