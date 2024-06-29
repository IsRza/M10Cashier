package is.rza.M10Cashier.exception;

import is.rza.M10Cashier.model.dto.ExceptionDTO;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppException extends RuntimeException {
    private String code;
    private String message;
    private HttpStatus status;

    public ExceptionDTO toDTO() {
        return new ExceptionDTO(message, code);
    }
}
