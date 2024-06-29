package is.rza.M10Cashier.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String phone,
        String name,
        BigDecimal balance,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {
}
