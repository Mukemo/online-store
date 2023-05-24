package com.ocprod.onlinestore.repository;

import com.ocprod.onlinestore.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
}
