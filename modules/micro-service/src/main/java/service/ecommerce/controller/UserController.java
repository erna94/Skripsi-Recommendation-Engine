package service.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.db.UserRepository;
import service.ecommerce.entities.User;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/")
	public List<User>getByUser() {
		// panggil database buat dapetin semua user
		List<User> u = new ArrayList<User>();

		u = userRepository.findSemuaUsers();

		return u;   
	}


	@PostMapping("")
	public User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}

}
