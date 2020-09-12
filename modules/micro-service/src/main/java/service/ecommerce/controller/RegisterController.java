package service.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.db.RegisterRepository;

import service.ecommerce.entities.Register;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
	
	private final RegisterRepository registerRepository;

	  RegisterController(RegisterRepository registerRepository) {
	    this.registerRepository = registerRepository;
	  }

	@PostMapping("/user")
	public Register newRegister(@RequestBody Register newRegister) {
	  return registerRepository.save(newRegister);
	  }

}
