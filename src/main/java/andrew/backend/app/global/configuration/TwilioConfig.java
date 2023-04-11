package andrew.backend.app.global.configuration;

import com.twilio.Twilio;
import com.twilio.http.TwilioRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Bean
    public TwilioRestClient twilio() {
        Twilio.init(accountSid, authToken);
        return Twilio.getRestClient();
    }
}
