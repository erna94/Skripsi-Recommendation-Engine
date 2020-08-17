package service.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import service.ecommerce.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findBySearch(Long idUser);
	
	@Query("SELECT u FROM Users u WHERE u.idUser = :userId" )
	List<User> findUserBySearch(@Param(value = "userId") Long userId);

}
