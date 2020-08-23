package service.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import service.ecommerce.entities.Category;
import service.ecommerce.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	@Query("SELECT u FROM User u WHERE u.idUser = :idUser")
	User findUserByID(@Param(value = "idUser") Long idUser);
	
	@Query("SELECT u FROM User u")
	List<User> findSemuaUsers();
}
