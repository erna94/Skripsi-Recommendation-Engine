package service.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import service.ecommerce.entities.Register;
import service.ecommerce.entities.User;

public interface RegisterRepository extends CrudRepository<Register, Long>{
	
	@Query("SELECT u FROM Register u WHERE u.idUser = :idUser")
	Register findRegisterByID(@Param(value = "idUser") Long idUser);
	
	@Query("SELECT u FROM Register u")
	List<Register> findSemuaRegister();
}
