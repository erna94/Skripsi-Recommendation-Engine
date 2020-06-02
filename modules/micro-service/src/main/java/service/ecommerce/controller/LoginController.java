package service.ecommerce.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.db.LoginRepository;
import service.ecommerce.entities.Login;



@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired
	LoginRepository loginRepository;
	
	@GetMapping("/{userId}/{password}")
    public Login getLogin(@PathVariable Long userId, String password) {
		// panggil database buat dapetin produk berdasarkan id yang di kasih
		Login u = getLoginFromDB(userId, password);
		
		//buat print variable userId & password
		System.out.println("UserId: " + userId + "Password: " + password);
		//print objek login yang dari database
		//buat print objek. ambil username & password dari Login u atau dari class Login.java
		System.out.println ("Username= " + u.getUserName());
		System.out.println ("Password= " + u.getPassword());
		
		if (userId > 0) {
			System.out.println("The number is positive.");
		}
		System.out.println("This statement is always executed");
		
        return u;
    }
	
	private Login getLoginFromDB(Long userId, String password) {
		// code buat panggil database
		Login u = loginRepository.findByIdUser(userId);
		
		return u;
	}
}

