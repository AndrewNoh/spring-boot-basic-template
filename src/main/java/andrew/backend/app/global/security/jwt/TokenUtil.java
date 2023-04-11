package andrew.backend.app.global.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import andrew.backend.app.global.security.auth.PrincipalDetails;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TokenUtil {


    public static String generateJwtToken(PrincipalDetails principalDetails, long expirationTime, String secret) {
        LocalDate localDate = LocalDate.now().plusDays(expirationTime);
        return JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .withClaim("email", principalDetails.getUserInfo().getEmail())
                .sign(Algorithm.HMAC512(secret));
    }

    public static DecodedJWT verifyJwtToken(String token, String secret) {
        return JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
    }

}

