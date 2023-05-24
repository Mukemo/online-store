package com.ocprod.onlinestore;

import com.ocprod.onlinestore.entity.Laptop;
import com.ocprod.onlinestore.entity.RamMemoryCard;
import com.ocprod.onlinestore.repository.LaptopRepository;
import com.ocprod.onlinestore.repository.RamMemoryCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class OnlineStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	@Order(1)
	@Bean
	CommandLineRunner saveData(@Autowired LaptopRepository laptopRepository, @Autowired RamMemoryCardRepository ramMemoryCardRepository){
		return args -> {

			Laptop macIntosh = new Laptop();
			macIntosh.setAvailableQty(10);
			macIntosh.setModelName("Mac Book air");
			macIntosh.setPrice(1000.0);
			laptopRepository.save(macIntosh);

			Laptop lonovo = new Laptop();
			lonovo.setAvailableQty(8);
			lonovo.setModelName("Lenovo");
			lonovo.setPrice(800.0);
			laptopRepository.save(lonovo);

			Laptop acer = new Laptop();
			acer.setAvailableQty(17);
			acer.setModelName("Acer");
			acer.setPrice(500.0);

			laptopRepository.save(acer);

			Laptop dell = new Laptop();
			dell.setAvailableQty(10);
			dell.setModelName("Acer");
			dell.setPrice(500.0);

			laptopRepository.save(dell);

			Laptop hp = new Laptop();
			hp.setAvailableQty(26);
			hp.setModelName("Acer");
			hp.setPrice(700.0);
			laptopRepository.save(hp);

			RamMemoryCard gb2 = new RamMemoryCard();
			gb2.setName("2 GB Ram");
			gb2.setLaptops(lonovo);
			ramMemoryCardRepository.save(gb2);

			RamMemoryCard gb4 = new RamMemoryCard();
			gb4.setName("4 GB Ram");
			gb4.setLaptops(dell);
			ramMemoryCardRepository.save(gb4);

			RamMemoryCard gb8 = new RamMemoryCard();
			gb8.setName("8 GB Ram");
			gb8.setLaptops(acer);
			ramMemoryCardRepository.save(gb8);

			RamMemoryCard gb16 = new RamMemoryCard();
			gb16.setName("16 GB Ram");
			gb16.setLaptops(macIntosh);
			ramMemoryCardRepository.save(gb16);

		};
	}
}
