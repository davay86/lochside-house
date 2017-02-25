package com.davidRonaldson.lochsideHouse.repositories;

import com.davidRonaldson.lochsideHouse.domain.Booking;
import com.davidRonaldson.lochsideHouse.domain.Customer;
import com.davidRonaldson.lochsideHouse.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking,Long> {

    List<Booking> findByRoom(Room room);
    List<Booking> findByCustomer(Customer customer);
}
