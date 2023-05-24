package com.ocprod.onlinestore.service;

import com.ocprod.onlinestore.dto.PurchaseDto;
import com.ocprod.onlinestore.entity.Laptop;
import com.ocprod.onlinestore.events.EmailEvent;
import com.ocprod.onlinestore.exceptions.ResourceNotFoundException;
import com.ocprod.onlinestore.model.PurchaseItemForm;
import com.ocprod.onlinestore.repository.LaptopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PurchaseService implements IPurchaseService{
    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private IEmailService emailService;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Override
    public PurchaseDto purchase(PurchaseItemForm purchaseItemForm){
        try{
            Optional<Laptop> laptop = laptopRepository.findById(purchaseItemForm.getItemId());
            if (!laptop.isPresent())
                throw new ResourceNotFoundException("No laptop found");
            if (laptop.get().getAvailableQty()<=0)
                throw new ResourceNotFoundException("laptop out of stock");
            if (purchaseItemForm.getQuantity()>laptop.get().getAvailableQty())
                throw new ResourceNotFoundException("You purchased " +purchaseItemForm.getQuantity() + " only " + laptop.get().getAvailableQty() + " remain in the stock." );
            // TODO purchase Item
            laptop.get().setAvailableQty(laptop.get().getAvailableQty() - purchaseItemForm.getQuantity());
            laptopRepository.save(laptop.get());
            PurchaseDto purchaseDto = new PurchaseDto();
            purchaseDto.setLaptopList(laptop.get());
            //facing the issue with the computer speed, i was unable to mock to rabbitmq instead i used event listener
            publisher.publishEvent(new EmailEvent());
            return purchaseDto;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
