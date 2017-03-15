package com.davidRonaldson.lochsideHouse.repositories;

import com.davidRonaldson.lochsideHouse.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room,Long> {
}
