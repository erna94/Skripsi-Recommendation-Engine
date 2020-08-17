package service.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import service.ecommerce.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUser();
	
	@Query("SELECT u FROM User u" )
	List<User>findByUserSemua();

}
