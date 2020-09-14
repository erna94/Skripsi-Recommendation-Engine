package service.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.db.RegisterRepository;

@RestController
@RequestMapping("/api")
public class RegisterController {
	
	private final RegisterRepository registerRepository;

	  RegisterController(RegisterRepository registerRepository) {
	    this.registerRepository = registerRepository;
	  }
}
