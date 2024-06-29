package is.rza.M10Cashier.service.totp;

public interface TOTPService {
    boolean validateTOTP(String secret, String otp);
}
