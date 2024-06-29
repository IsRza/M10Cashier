package is.rza.M10Cashier.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedTransaction extends AppException {
    public UnauthorizedTransaction() {
        super(
                "unauthorized-transaction",
                "Unauthorized transaction detected",
                HttpStatus.UNAUTHORIZED
        );
    }
}
