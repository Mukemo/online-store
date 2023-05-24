package com.ocprod.onlinestore.dto;

import com.ocprod.onlinestore.entity.Laptop;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PurchaseDto {
    private String modelName;
    private double totalAmount;
    private int quantity;
}
