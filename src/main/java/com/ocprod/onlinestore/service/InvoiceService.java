package com.ocprod.onlinestore.service;

import com.ocprod.onlinestore.model.InvoiceForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class InvoiceService implements IInvoiceService{
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void payment(InvoiceForm invoiceForm, String urlPaymentService) {

    }
}
