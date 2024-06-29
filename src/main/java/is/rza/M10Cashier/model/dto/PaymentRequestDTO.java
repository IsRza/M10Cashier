package is.rza.M10Cashier.model.dto;

import java.math.BigDecimal;

public record PaymentRequestDTO(
        Long userId,
        String otp,
        BigDecimal amount
) {
}
