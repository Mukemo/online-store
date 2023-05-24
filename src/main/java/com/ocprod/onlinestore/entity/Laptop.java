package com.ocprod.onlinestore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelName;
    private int availableQty;
    private double price;
    @OneToMany
    private List<RamMemoryCard> ramMemoryCard;
}
