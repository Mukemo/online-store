package com.ocprod.onlinestore.service;

import com.ocprod.onlinestore.model.EmailForm;

public interface IEmailService {
    public void sendEmail(EmailForm emailForm);
}
