package com.ocprod.onlinestore.service;

import com.ocprod.onlinestore.dto.PurchaseDto;
import com.ocprod.onlinestore.entity.Laptop;
import com.ocprod.onlinestore.entity.RamMemoryCard;
import com.ocprod.onlinestore.events.EmailEvent;
import com.ocprod.onlinestore.exceptions.ResourceNotFoundException;
import com.ocprod.onlinestore.model.EmailForm;
import com.ocprod.onlinestore.model.InvoiceForm;
import com.ocprod.onlinestore.model.PurchaseItemForm;
import com.ocprod.onlinestore.repository.LaptopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class PurchaseService implements IPurchaseService{
    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private IEmailService emailService;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private IInvoiceService invoiceService;
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
            purchaseDto.setModelName(laptop.get().getModelName());
            purchaseDto.setQuantity(purchaseItemForm.getQuantity());
            purchaseDto.setTotalAmount(laptop.get().getPrice() * purchaseItemForm.getQuantity());
            //facing the issue with the computer speed, i was unable to mock to rabbitmq instead i used event listener
            publisher.publishEvent(new EmailEvent());
            invoiceService.payment(new InvoiceForm(laptop.get().getModelName(), purchaseItemForm.getQuantity(), (laptop.get().getPrice()*purchaseItemForm.getQuantity())),"http://payment-url.com");
            List<String> rams = new ArrayList<>();
            laptop.get().getRamMemoryCard().stream().forEach(ramMemoryCard -> rams.add(ramMemoryCard.getName()));
            emailService.sendEmail(new EmailForm("Computer purchase", rams));
            return purchaseDto;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
