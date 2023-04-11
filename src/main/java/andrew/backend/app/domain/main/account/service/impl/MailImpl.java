package andrew.backend.app.domain.main.account.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import andrew.backend.app.domain.main.account.model.dto.join.RequestMailDto;
import andrew.backend.app.domain.main.account.service.MailService;
import andrew.backend.app.global.configuration.mail.MailHandler;
import andrew.backend.app.global.util.CertificationRandomNumber;

import javax.mail.MessagingException;

@Service
@Transactional
@RequiredArgsConstructor
public class MailImpl implements MailService {
    private final MailHandler mailHandler;

    @Override
    public String sendSms(RequestMailDto req) throws MessagingException {
        String random = CertificationRandomNumber.randomNumber();
        String htmlContent;
        mailHandler.setFrom();
        mailHandler.setTo(req.getEmail());
        if(req.isKorea()){
            mailHandler.setSubject("인증 코드는 " + random + "입니다.");
            htmlContent = "<div style=\"background-color: #f2f2f2; padding: 20px;\">" +
                    "<div style=\"background-color: #ffffff; max-width: 600px; margin: 0 auto; border-radius: 5px; box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);\">" +
                    "<div style=\"background-color: #111111; color: #ffffff; padding: 20px; border-radius: 5px 5px 0 0;\">" +
                    "<h1 style=\"margin: 0; font-size: 26px; font-family: Arial, sans-serif;\">이메일 인증하기</h1>" +
                    "</div>" +
                    "<div style=\"padding: 20px;\">" +
                    "<p style=\"margin-bottom: 20px; font-size: 18px; font-family: Arial, sans-serif;\">1eco 계정 이메일 인증을 위해 아래 코드를 입력창에 입력해주세요.</p>" +
                    "<div style=\"background-color: #ffffff; border-radius: 5px; padding: 12px; box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);\">" +
                    "<p style=\"margin: 0; font-weight: bold; font-size: 18px; font-family: Arial, sans-serif;\">코드: " + random + "</p>" +
                    "</div>" +
                    "<p style=\"margin-top: 20px; font-size: 16px; font-family: Arial, sans-serif;\">감사합니다.</p>" +
                    "<p style=\"margin-bottom: 0; font-size: 14px; font-family: Arial, sans-serif;\">~~~~</p>" +
                    "</div>" +
                    "</div>" +
                    "</div>";
        }else{
            mailHandler.setSubject("code - " + random);
            htmlContent = "<div style=\"background-color: #f2f2f2; padding: 20px;\">" +
                    "<div style=\"background-color: #ffffff; max-width: 600px; margin: 0 auto; border-radius: 5px; box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);\">" +
                    "<div style=\"background-color: #111111; color: #ffffff; padding: 20px; border-radius: 5px 5px 0 0;\">" +
                    "<h1 style=\"margin: 0; font-size: 26px; font-family: Arial, sans-serif;\">Email verification</h1>" +
                    "</div>" +
                    "<div style=\"padding: 20px;\">" +
                    "<p style=\"margin-bottom: 20px; font-size: 18px; font-family: Arial, sans-serif;\">Use it to verify your email in 1eco world.</p>" +
                    "<div style=\"background-color: #ffffff; border-radius: 5px; padding: 12px; box-shadow: 0 3px 3px rgba(0, 0, 0, 0.2);\">" +
                    "<p style=\"margin: 0; font-weight: bold; font-size: 18px; font-family: Arial, sans-serif;\">Code: " + random + "</p>" +
                    "</div>" +
                    "<p style=\"margin-top: 20px; font-size: 16px; font-family: Arial, sans-serif;\">Yours,</p>" +
                    "<p style=\"margin-bottom: 0; font-size: 14px; font-family: Arial, sans-serif;\">~~~~</p>" +
                    "</div>" +
                    "</div>" +
                    "</div>";
        }

        mailHandler.setText(htmlContent, true);
        mailHandler.send();


        return random;
    }

}
