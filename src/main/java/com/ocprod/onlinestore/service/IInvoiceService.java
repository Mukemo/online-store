package com.ocprod.onlinestore.service;

import com.ocprod.onlinestore.model.InvoiceForm;

public interface IInvoiceService {
    public void payment(InvoiceForm invoiceForm, String urlPaymentService);
}
