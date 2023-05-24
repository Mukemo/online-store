package com.ocprod.onlinestore.service;

import com.ocprod.onlinestore.dto.PurchaseDto;
import com.ocprod.onlinestore.model.PurchaseItemForm;

public interface IPurchaseService {
    PurchaseDto purchase(PurchaseItemForm purchaseItemForm);
}
