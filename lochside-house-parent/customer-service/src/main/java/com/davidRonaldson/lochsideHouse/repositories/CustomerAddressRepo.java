package com.davidRonaldson.lochsideHouse.repositories;


import com.davidRonaldson.lochsideHouse.domain.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepo extends JpaRepository<CustomerAddress, Long> {
}
