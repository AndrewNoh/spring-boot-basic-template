package andrew.backend.app.global.configuration.mail;

import andrew.backend.app.global.exception.custom.NotFoundEmailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component("mailHandler")
public class MailHandler {
    @Value("${javax.mail.id}")
    private String email;
    private final JavaMailSender sender;
    private final MimeMessage message;
    private final MimeMessageHelper messageHelper;

    public MailHandler(JavaMailSender mailSender) throws MessagingException {
        this.sender = mailSender;
        message = mailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "utf-8");
    }

    public void setFrom() throws MessagingException {
        messageHelper.setFrom(email);
        System.out.println(email);
    }

    public void setTo(String email) throws MessagingException {
        messageHelper.setTo(email);
    }

    // 제목
    public void setSubject(String subject) throws MessagingException {
        messageHelper.setSubject(subject);
    }

    // 메일 내용
    public void setText(String text, boolean useHtml) throws MessagingException {
        messageHelper.setText(text, useHtml);
    }

    public void send() {
        try {
            sender.send(message);
        } catch (Exception e) {
            throw new NotFoundEmailException("이메일 주소를 찾을 수 없습니다.");
        }
    }

}