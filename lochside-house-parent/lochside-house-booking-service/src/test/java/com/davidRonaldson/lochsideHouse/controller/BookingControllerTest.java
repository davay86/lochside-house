package com.davidRonaldson.lochsideHouse.controller;

import com.davidRonaldson.lochsideHouse.domain.Booking;
import com.davidRonaldson.lochsideHouse.domain.Customer;
import com.davidRonaldson.lochsideHouse.domain.Room;
import com.davidRonaldson.lochsideHouse.repositories.BookingRepo;
import com.davidRonaldson.lochsideHouse.repositories.CustomerRepo;
import com.davidRonaldson.lochsideHouse.repositories.RoomRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BookingController.class)
public class BookingControllerTest {

    @Autowired
    BookingController bookingController;

    @MockBean
    CustomerRepo customerRepo;

    @MockBean
    RoomRepo roomRepo;

    @MockBean
    BookingRepo bookingRepo;

    Booking booking;
    Customer customer;
    Room room;

    @Before
    public void setUp() throws Exception {
        customer = new Customer();
        customer.setId(456L);
        room = new Room();
        room.setId(123L);
        booking = new Booking(customer,room, Date.valueOf("2017-07-09"),Date.valueOf("2017-07-18"));
        booking.setId(789L);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getBookingsForRoom() throws Exception {

        when(roomRepo.findOne(123L)).thenReturn(room);
        when(bookingRepo.findByRoom(room)).thenReturn(Arrays.asList(booking));

        List<Booking> foundBookings = bookingController.getBookingsForRoom("123");

        Assert.assertEquals(1,foundBookings.size());
        Assert.assertEquals(booking,foundBookings.get(0));

        verify(roomRepo,times(1)).findOne(123L);
        verify(bookingRepo,times(1)).findByRoom(room);

    }

    @Test
    public void getCustomerBookings() throws Exception {

        when(customerRepo.findOne(456L)).thenReturn(customer);
        when(bookingRepo.findByCustomer(customer)).thenReturn(Arrays.asList(booking));

        List<Booking> foundBookings = bookingController.getCustomerBookings("456");

        Assert.assertEquals(1,foundBookings.size());
        Assert.assertEquals(booking,foundBookings.get(0));

        verify(customerRepo,times(1)).findOne(456L);
        verify(bookingRepo,times(1)).findByCustomer(customer);
    }

    @Test
    public void getAvailability_roomAvailable() throws Exception {

        when(roomRepo.findOne(123L)).thenReturn(room);
        when(bookingRepo.findByRoom(room)).thenReturn(Arrays.asList(booking));

        String availability = bookingController.getAvailability("123","2017-05-01","2017-05-02");

        Assert.assertEquals("Room available", availability);

    }

    @Test
    public void getAvailability_roomNotAvailable() throws Exception {

        when(roomRepo.findOne(123L)).thenReturn(room);
        when(bookingRepo.findByRoom(room)).thenReturn(Arrays.asList(booking));

        String availability = bookingController.getAvailability("123","2017-07-10","2017-07-15");

        Assert.assertEquals("Date unavailable",availability);
    }

    @Test
    public void getAvailability_roomNotAvailable_partiallyBlocked() throws Exception {

        when(roomRepo.findOne(123L)).thenReturn(room);
        when(bookingRepo.findByRoom(room)).thenReturn(Arrays.asList(booking));

        String availability = bookingController.getAvailability("123","2017-07-01","2017-07-30");

        Assert.assertEquals("Date unavailable",availability);
    }

    @Test
    public void createBooking() throws Exception {

        when(customerRepo.findOne(456L)).thenReturn(customer);
        when(roomRepo.findOne(123L)).thenReturn(room);

        bookingController.createBooking("123","456","2017-03-04","2017-03-06");

        verify(customerRepo,times(1)).findOne(456L);
        verify(roomRepo,times(1)).findOne(123L);
        verify(bookingRepo,times(1)).save(any(Booking.class));

    }

}