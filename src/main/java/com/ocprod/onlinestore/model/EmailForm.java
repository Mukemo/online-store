package com.ocprod.onlinestore.model;

import lombok.Data;

@Data
public class EmailForm {
    private String title;
    private String receiver;
    private String sender;
    private String body;
}
