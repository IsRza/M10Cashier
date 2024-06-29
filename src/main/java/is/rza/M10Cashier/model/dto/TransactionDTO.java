package is.rza.M10Cashier.model.dto;

import is.rza.M10Cashier.model.enums.EntryType;

import java.math.BigDecimal;

public record TransactionDTO(
        Long id,
        UserDTO payerUser,
        BigDecimal amount,
        EntryType entryType
) {
}
