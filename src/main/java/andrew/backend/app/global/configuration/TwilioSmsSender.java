package andrew.backend.app.global.configuration;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.twilio.exception.TwilioException;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import andrew.backend.app.domain.main.account.model.dto.join.RequestPhoneNumberDto;
import andrew.backend.app.global.util.CertificationRandomNumber;

@Component
@RequiredArgsConstructor
public class TwilioSmsSender {

    private final TwilioRestClient twilio;
    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;

    public String sendSms(RequestPhoneNumberDto req) throws TwilioException {
        String formattedPhoneNumber = formatPhoneNumber(req.getPhoneNumber(), req.getCountryCode());
        String authenticationNumber = CertificationRandomNumber.randomNumber();
        String messageBody = "[Empty]\nCode: " + authenticationNumber;
        Message.creator(new PhoneNumber(formattedPhoneNumber), new PhoneNumber(twilioPhoneNumber), messageBody).create();
        return authenticationNumber;
    }


    private String formatPhoneNumber(String phoneNumber, String countryNumber) {
        String formattedPhoneNumber;
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber number = new Phonenumber.PhoneNumber();
        number.setNationalNumber(Long.parseLong(phoneNumber));
        number.setCountryCode(Integer.parseInt(countryNumber));
        if (phoneNumberUtil.isValidNumber(number)) {
            formattedPhoneNumber = phoneNumberUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
        } else {
            throw new IllegalArgumentException("Invalid phone number");
        }
        return formattedPhoneNumber;
    }

}
