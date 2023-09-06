package andrew.backend.app.global.security.sns;

import andrew.backend.app.domain.main.account.model.enums.SnsProvider;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Component
public class SnsTokenVerifier {

    public String getSnsIdFromToken(SnsProvider provider, String accessToken) {
        switch (provider) {
            case GOOGLE:
                return getGoogleIdFromToken(accessToken);
            case APPLE:
                return getAppleIdFromToken(accessToken);
            default:
                throw new IllegalArgumentException("Invalid SNS provider: " + provider);
        }
    }

    private String getGoogleIdFromToken(String accessToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new JacksonFactory()).setAudience(Collections.singletonList("1075538420882-mas8cedp3j30h68n3dm8gao4khm8gurs.apps.googleusercontent.com")).build();
            GoogleIdToken idToken = verifier.verify(accessToken);
            if (idToken != null) {
                return idToken.getPayload().getSubject();
            }
            throw new InvalidTokenException("Failed to verify Google access token");
        } catch (GeneralSecurityException | IOException e) {
            throw new InvalidTokenException("Failed to verify Google access token", e);
        }
    }

    private String getAppleIdFromToken(String accessToken) {
        // Apple access token is already the Apple ID, no need to extract
        return accessToken;
    }
}
