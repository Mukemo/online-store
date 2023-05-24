package com.ocprod.onlinestore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceForm {
    private String productName;
    private int productQty;
    private double totalAmount;
}
