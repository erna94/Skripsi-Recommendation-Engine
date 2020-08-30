package service.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import service.ecommerce.entities.Login;
import service.ecommerce.entities.Product;


public interface LoginRepository extends CrudRepository<Login, Long>{

	/***
	 * Mencari satu product dengan suatu product ID tertentu
	 * @param id
	 * @return
	 */
	  Login findByIdUser(Long idUser);
	  
	  @Query("SELECT u FROM Login u WHERE u.userName = :userName" )
	  Login findLoginByUserName(@Param(value = "userName") String userName);
	  
	  @Query("SELECT u FROM Login u WHERE u.password = :password")
	  List<Login> findLoginByPassword(@Param(value = "password") String password);
	  
}
