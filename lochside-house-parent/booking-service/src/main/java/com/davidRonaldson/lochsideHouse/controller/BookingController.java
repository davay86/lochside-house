package com.davidRonaldson.lochsideHouse.controller;

import com.davidRonaldson.lochsideHouse.domain.Booking;
import com.davidRonaldson.lochsideHouse.domain.Customer;
import com.davidRonaldson.lochsideHouse.domain.Room;
import com.davidRonaldson.lochsideHouse.repositories.BookingRepo;
import com.davidRonaldson.lochsideHouse.repositories.CustomerRepo;
import com.davidRonaldson.lochsideHouse.repositories.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    BookingRepo bookingRepo;

    @RequestMapping(value = "/roomBookings", method = RequestMethod.GET)
    public List<Booking> getBookingsForRoom(@RequestParam(value="room") String id){
        return bookingRepo.findByRoom(roomRepo.findOne(Long.valueOf(id)));
    }

    @RequestMapping(value="/customerBookings", method = RequestMethod.GET)
    public List<Booking> getCustomerBookings(@RequestParam(value="customer") String id){
        return bookingRepo.findByCustomer(customerRepo.findOne(Long.valueOf(id)));
    }

    @RequestMapping(value = "/availability", method = RequestMethod.GET)
    public  String getAvailability(@RequestParam(value="room",defaultValue = "0") String roomId, @RequestParam(value="from") String fromDate, @RequestParam(value="to") String toDate){
        Date from = Date.valueOf(fromDate);
        Date to = Date.valueOf(toDate);

        List<Booking> bookingsForRoom = bookingRepo.findByRoom(roomRepo.findOne(Long.valueOf(roomId)));
        for (Booking booking : bookingsForRoom) {
            if(booking.getFromDate().before(from)){
                if(booking.getToDate().after(from)){
                    return "Date unavailable";
                }
            }else if(booking.getFromDate().before(to)){
                return "Date unavailable";
            }
        }
        return "Room available";
    }

    @RequestMapping(value = "/createBooking", method = RequestMethod.POST)
    public void createBooking(@RequestParam(value="room") String roomId, @RequestParam(value="customer") String customerId, @RequestParam(value="fromDate") String fromDate, @RequestParam(value="toDate") String toDate){
        Room room = roomRepo.findOne(Long.valueOf(roomId));
        Customer customer = customerRepo.findOne(Long.valueOf(customerId));
        Date from = Date.valueOf(fromDate);
        Date to = Date.valueOf(toDate);

        Booking newBooking = new Booking(customer,room,from,to);

        bookingRepo.save(newBooking);
    }
}
