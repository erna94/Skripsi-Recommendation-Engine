package service.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import service.ecommerce.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	@Query("SELECT u FROM User u")
		List<User>findByUserSemua();
}
