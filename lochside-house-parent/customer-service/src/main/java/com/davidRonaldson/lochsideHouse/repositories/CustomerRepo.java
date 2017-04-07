package com.davidRonaldson.lochsideHouse.repositories;

import com.davidRonaldson.lochsideHouse.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
