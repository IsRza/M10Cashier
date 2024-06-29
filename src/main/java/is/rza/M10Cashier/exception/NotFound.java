package is.rza.M10Cashier.exception;

import org.springframework.http.HttpStatus;

public class NotFound extends AppException {
    public NotFound() {
        super("not-found", "Record not found", HttpStatus.NOT_FOUND);
    }
}
