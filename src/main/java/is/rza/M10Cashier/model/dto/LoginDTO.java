package is.rza.M10Cashier.model.dto;

public record LoginDTO(
        String phone,
        String password,
        String secret
) {
}
