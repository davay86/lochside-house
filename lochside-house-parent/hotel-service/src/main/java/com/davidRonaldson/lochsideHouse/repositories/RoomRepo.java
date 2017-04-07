package com.davidRonaldson.lochsideHouse.repositories;

import com.davidRonaldson.lochsideHouse.domain.Hotel;
import com.davidRonaldson.lochsideHouse.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepo extends JpaRepository<Room, Long> {

    List<Room> findByHotel(Hotel hotel);
}
