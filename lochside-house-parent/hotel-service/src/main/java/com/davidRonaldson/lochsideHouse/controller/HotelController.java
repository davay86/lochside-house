package com.davidRonaldson.lochsideHouse.controller;

import com.davidRonaldson.lochsideHouse.domain.Hotel;
import com.davidRonaldson.lochsideHouse.domain.Room;
import com.davidRonaldson.lochsideHouse.repositories.HotelRepo;
import com.davidRonaldson.lochsideHouse.repositories.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    HotelRepo hotelRepo;

    @Autowired
    RoomRepo roomRepo;

    @RequestMapping(value = "/hotel", method = RequestMethod.GET)
    public Hotel getHotel(@RequestParam(value = "hotel") String id) {
        return hotelRepo.findOne(Long.valueOf(id));
    }

    @RequestMapping(value = "/hotels", method = RequestMethod.GET)
    public List<Hotel> getAllHotels() {
        return hotelRepo.findAll();
    }

    @RequestMapping(value = "/hotelRooms", method = RequestMethod.GET)
    public List<Room> getAllHotels(@RequestParam(value = "hotel") String id) {
        return roomRepo.findByHotel(hotelRepo.findOne(Long.valueOf(id)));
    }

}
