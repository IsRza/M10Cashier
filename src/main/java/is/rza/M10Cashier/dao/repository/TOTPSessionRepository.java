package is.rza.M10Cashier.dao.repository;

import is.rza.M10Cashier.dao.entity.TOTPSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TOTPSessionRepository extends JpaRepository<TOTPSessionEntity, Long> {
    Optional<TOTPSessionEntity> findByPayerUserId(Long userId);
}
