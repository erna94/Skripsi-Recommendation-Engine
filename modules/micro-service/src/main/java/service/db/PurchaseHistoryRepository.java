package service.db;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import service.ecommerce.entities.PurchaseHistory;


public interface PurchaseHistoryRepository extends CrudRepository<PurchaseHistory, Long> {

	@Query("SELECT u FROM PurchaseHistory u")
	List<PurchaseHistory>findBySemuaPurchase();
}
