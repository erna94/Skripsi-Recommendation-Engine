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
    public Login getLogin(@PathVariable Long userId, @PathVariable String password) {
		// panggil database buat dapetin produk berdasarkan id yang di kasih
		Login u = getLoginFromDB(userId, password);
		
		//buat print variable userId & password
		System.out.println("UserId: " + userId + " Password: " + password);
		//print objek login yang dari database
		//buat print objek. dapetin username & password dari Login u atau dari class Login.java
		System.out.println ("Username= " + u.getUserName());
		System.out.println ("Password= " + u.getPassword());
		
		//ganti variable nama dgn parameter password
		String nama = password ;
		String nama2 = u.getPassword() ;
		
		//membandingkan variable nama dgn variable nama2 apakah isinya sama atau tidak
		if (nama.equals(nama2)) {
			
			System.out.println(",kedua variable mempunyai nilai sama");
			
			return u;
		}
		
		else {
			Login u2 = getLoginFromDB(userId, password);
			
			System.out.println(",kedua variable mempunyai nilai berbeda");
			
			return u2;
        }
        
	}
	
	
	private Login getLoginFromDB(Long userId, String password) {
		// code buat panggil database
		Login u = loginRepository.findByIdUser(userId);
		
		return u;
	}
}

