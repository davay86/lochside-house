package com.davidRonaldson.lochsideHouse.repositories;

import com.davidRonaldson.lochsideHouse.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepo extends JpaRepository<Hotel, Long> {

}
