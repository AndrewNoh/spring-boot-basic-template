package andrew.backend.app.domain.main.account.service;


import andrew.backend.app.domain.main.account.model.dto.join.RequestMailDto;

import javax.mail.MessagingException;

public interface MailService {

    abstract String sendSms(RequestMailDto req) throws MessagingException;
}
