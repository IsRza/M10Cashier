package is.rza.M10Cashier.service.totp;

import is.rza.M10Cashier.exception.InternalServerError;
import is.rza.M10Cashier.util.TOTPUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TOTPServiceImpl implements TOTPService {
    private final TOTPUtil totpUtil;
    @Override
    public boolean validateTOTP(String secret, String otp) {
        try {
            var actualOTP = totpUtil.generate(secret, new Date());
            return actualOTP.equals(otp);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new InternalServerError();
        }
    }
}
