package com.ocprod.onlinestore.events;

import lombok.Data;

@Data
public class EmailEvent {
    private String title;
    private String message;
}
