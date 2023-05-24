package com.ocprod.onlinestore.controller;

import com.ocprod.onlinestore.dto.PurchaseDto;
import com.ocprod.onlinestore.model.PurchaseItemForm;
import com.ocprod.onlinestore.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;
    @GetMapping("/purchase")
    public ResponseEntity<PurchaseDto> purchase(@RequestBody PurchaseItemForm purchaseItemForm){
        return  ResponseEntity.ok(purchaseService.purchase(purchaseItemForm));
    }
}
