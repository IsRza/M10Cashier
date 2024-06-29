package is.rza.M10Cashier.exception;

import org.springframework.http.HttpStatus;

public class InternalServerError extends AppException {
    public InternalServerError() {
        super(
                "internal-server-error",
                "An internal server error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
