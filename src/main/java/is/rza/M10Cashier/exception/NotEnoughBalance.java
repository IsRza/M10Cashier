package is.rza.M10Cashier.exception;

import org.springframework.http.HttpStatus;

public class NotEnoughBalance extends AppException {
    public NotEnoughBalance() {
        super(
                "not-enough-balance",
                "Not enough amount is in balance to make transaction",
                HttpStatus.BAD_REQUEST
        );
    }
}
