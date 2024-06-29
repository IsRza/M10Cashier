package is.rza.M10Cashier.exception;

import org.springframework.http.HttpStatus;

public class InvalidCredentials extends AppException {
    public InvalidCredentials() {
        super(
                "invalid-credentials",
                "Phone number and password does not match",
                HttpStatus.UNAUTHORIZED
        );
    }
}
