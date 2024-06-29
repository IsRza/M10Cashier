package is.rza.M10Cashier.dao.repository;

import is.rza.M10Cashier.dao.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
