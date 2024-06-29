package is.rza.M10Cashier.exception;

import is.rza.M10Cashier.model.dto.ExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private final Logger log = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ExceptionDTO> handleAppException(AppException ae) {
        log.error(ae.getMessage());
        return new ResponseEntity<>(
                ae.toDTO(),
                ae.getStatus()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(
                new InternalServerError().toDTO(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
