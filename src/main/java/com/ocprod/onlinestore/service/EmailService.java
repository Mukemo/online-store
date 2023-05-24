package com.ocprod.onlinestore.service;

import com.ocprod.onlinestore.model.EmailForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements IEmailService{
    @Override
    public void sendEmail(EmailForm emailForm) {
      log.info("Send email");
    }
}
