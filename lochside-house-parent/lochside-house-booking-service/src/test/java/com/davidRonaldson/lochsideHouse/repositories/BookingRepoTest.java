package com.davidRonaldson.lochsideHouse.repositories;

import com.davidRonaldson.lochsideHouse.config.RepositoryConfig;
import com.davidRonaldson.lochsideHouse.domain.Booking;
import com.davidRonaldson.lochsideHouse.domain.Customer;
import com.davidRonaldson.lochsideHouse.domain.Room;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class BookingRepoTest {

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    RoomRepo roomRepo;

    Booking booking;
    Customer customer;
    Room room;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Test Customer");
        room = new Room("Single",100);
        booking = new Booking(customer,room, Date.valueOf("2017-05-09"),Date.valueOf("2017-05-16"));

        customerRepo.save(customer);
        roomRepo.save(room);
        bookingRepo.save(booking);

    }

    @After
    public void tearDown() throws Exception {
        bookingRepo.delete(booking);
        customerRepo.delete(customer);
        roomRepo.delete(room);

    }

    @Test
    public void findByRoom() throws Exception {
        List<Booking> bookings = bookingRepo.findByRoom(room);
        Assert.assertEquals(1,bookings.size());

        Booking foundBooking = bookings.get(0);
        Assert.assertEquals(customer.getId(),foundBooking.getCustomer().getId());
        Assert.assertEquals(room.getId(),foundBooking.getRoom().getId());
        Assert.assertEquals(booking.getFromDate(),foundBooking.getFromDate());
        Assert.assertEquals(booking.getToDate(),foundBooking.getToDate());
    }

    @Test
    public void findByCustomer() throws Exception {
        List<Booking> bookings = bookingRepo.findByCustomer(customer);
        Assert.assertEquals(1,bookings.size());

        Booking foundBooking = bookings.get(0);
        Assert.assertEquals(customer.getId(),foundBooking.getCustomer().getId());
        Assert.assertEquals(room.getId(), foundBooking.getRoom().getId());
        Assert.assertEquals(booking.getToDate(),foundBooking.getToDate());
        Assert.assertEquals(booking.getFromDate(),foundBooking.getFromDate());

    }

}