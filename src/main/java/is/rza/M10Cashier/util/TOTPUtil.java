package is.rza.M10Cashier.util;

import is.rza.M10Cashier.exception.InternalServerError;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

@Component
public class TOTPUtil {
    private static final short OTP_LENGTH = 8;
    private static final short OTP_STEP = 30;
    private static final String OTP_ALGO = "HmacSHA512";


    public String generate(String secret, Date time) throws GeneralSecurityException {
        long secondsPast1970 = time.getTime() / 1000;
        if (!validateTime(secondsPast1970)) {
            throw new InternalServerError();
        }
        long counterValue = secondsPast1970 / OTP_STEP;
        return generate(secret.getBytes(), counterValue);
    }

    private boolean validateTime(long time) {
        return time > 0;
    }

    private String generate(byte[] secret, long counter) throws GeneralSecurityException, NoSuchAlgorithmException {
        // Convert counter to big endian byte array
        byte[] counterBytes = ByteBuffer.allocate(8)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(counter).array();

        // Create HMAC hash
        Mac mac = Mac.getInstance(OTP_ALGO);
        SecretKeySpec keySpec = new SecretKeySpec(secret, OTP_ALGO);
        mac.init(keySpec);
        byte[] hmac = mac.doFinal(counterBytes);

        // Get last 4 bits of hash as offset
        int offset = hmac[hmac.length - 1] & 0x0f;

        // Get 4 bytes from the hash from [offset] to [offset + 3]
        byte[] truncatedHMAC = Arrays.copyOfRange(hmac, offset, offset + 4);

        // Convert byte array of the truncated hash to integer
        int number = ByteBuffer.wrap(truncatedHMAC).order(ByteOrder.BIG_ENDIAN).getInt();

        // Mask most significant bit
        number &= 0x7fffffff;

        // Modulo number by 10^(digits)
        number %= (int) Math.pow(10, OTP_LENGTH);

        // Convert int to string
        StringBuilder strNum = new StringBuilder(Integer.toString(number));

        // Add leading zeros if necessary
        while (strNum.length() < OTP_LENGTH) {
            strNum.insert(0, "0");
        }

        return strNum.toString();
    }
}

/*

    public String generate(String secret, Date time) throws GeneralSecurityException {
//        long secondsPast1970 = time.getTime() / 1000;
        long secondsPast1970 = 765434567;
        if (!validateTime(secondsPast1970)) {
            return null;
        }
        long counterValue = secondsPast1970 / OTP_STEP;
        return generateOTP(secret.getBytes(), OTPAlgorithm.SHA1, counterValue, OTP_LENGTH);
    }

    private boolean validateTime(long time) {
        return time > 0;
    }

    private String generateOTP(
            byte[] secret, OTPAlgorithm algorithm, long counter, int digits
    ) throws GeneralSecurityException, NoSuchAlgorithmException {
        // Convert counter to big endian byte array
        byte[] counterBytes = ByteBuffer.allocate(8)
                .order(ByteOrder.BIG_ENDIAN)
                .putLong(counter).array();

        // Create HMAC hash
        Mac mac = Mac.getInstance(algorithm.getAlgorithm());
        SecretKeySpec keySpec = new SecretKeySpec(secret, algorithm.getAlgorithm());
        mac.init(keySpec);
        byte[] hmac = mac.doFinal(counterBytes);

        // Get last 4 bits of hash as offset
        int offset = hmac[hmac.length - 1] & 0x0f;

        // Get 4 bytes from the hash from [offset] to [offset + 3]
        byte[] truncatedHMAC = Arrays.copyOfRange(hmac, offset, offset + 4);

        // Convert byte array of the truncated hash to integer
        int number = ByteBuffer.wrap(truncatedHMAC).order(ByteOrder.BIG_ENDIAN).getInt();

        // Mask most significant bit
        number &= 0x7fffffff;

        // Modulo number by 10^(digits)
        number %= (int) Math.pow(10, digits);

        // Convert int to string
        String strNum = Integer.toString(number);

        // Add leading zeros if necessary
        while (strNum.length() < digits) {
            strNum = "0" + strNum;
        }

        return strNum;
    }
 */