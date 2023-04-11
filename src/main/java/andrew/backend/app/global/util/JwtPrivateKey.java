package andrew.backend.app.global.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtPrivateKey {
    public static int EXPIRATION_TIME;

    @Value("${jwt.expiration.day}")
    public void setExpirationTime(int value) {
        EXPIRATION_TIME = value;
    }

    public static String HEADER_STRING;

    @Value("${jwt.header.string}")
    public void setHeaderString(String value) {
        HEADER_STRING = value;
    }

    public static String TOKEN_PREFIX;

    @Value("${jwt.token.prefix}")
    public void setTokenPrefix(String value) {
        TOKEN_PREFIX = value;
    }

    public static String SECRET;

    @Value("${jwt.secret.key}")
    public void setSECRET(String value) {
        SECRET = value;
    }


}
