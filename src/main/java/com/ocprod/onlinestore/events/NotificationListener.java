package com.ocprod.onlinestore.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationListener {
    @EventListener
    public void handler(EmailEvent notificationEvent){
        log.info("Notification send");
    }
}
