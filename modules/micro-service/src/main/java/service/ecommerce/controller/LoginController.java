package service.ecommerce.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.db.LoginRepository;
import service.ecommerce.entities.Login;
import service.ecommerce.entities.User;



@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	LoginRepository loginRepository;

	@GetMapping("/{userName}/{password}")
	public Login getLogin(@PathVariable String userName, @PathVariable String password) {

		System.out.println(">>>>>>>>>>>>> Memanggil login buat " + userName);

		// panggil database buat dapetin produk berdasarkan id yang di kasih
		Login u = getLoginFromDB(userName, password);

		if(u != null) {
			//buat print variable userId & password
			System.out.println("UserId: " + u.getIdUser() + " Password: " + password);
			//print objek login yang dari database
			//buat print objek. dapetin username & password dari Login u atau dari class Login.java
			System.out.println ("Username= " + u.getUserName());
			System.out.println ("Password= " + u.getPassword());

			//ganti variable nama dgn parameter password
			String nama = password ;
			String nama2 = u.getPassword() ;

			//membandingkan variable nama dgn variable nama2 apakah isinya sama atau tidak
			if (nama.equals(nama2)) {
				System.out.println("success");
				return u;
			} else {
				//buat objek login kosong karena salah password
				System.out.println(">>>>>>>>>>>>> Salah password untuk username " + userName);
				
				Login u2 = new Login();	
				return u2;
			}  
		} else {
			//buat objek login kosong karena tidak ada user sesuai
			System.out.println(">>>>>>>>>>>>> Tidak ketemu userid untuk username " + userName);
			Login u2 = new Login();	
			return u2;
		}     
	}
	
	
	@PostMapping("")
	public Login newLogin(@RequestBody Login newLogin) {
		return loginRepository.save(newLogin);
	}

	private Login getLoginFromDB(String userName, String password) {
		// code buat panggil database
		Login u = loginRepository.findLoginByUserName(userName);
		return u;
	}
}

