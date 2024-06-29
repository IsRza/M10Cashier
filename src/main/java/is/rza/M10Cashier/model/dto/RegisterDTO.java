package is.rza.M10Cashier.model.dto;

public record RegisterDTO(
        String phone,
        String name,
        String password,
        String secret
) {
}
