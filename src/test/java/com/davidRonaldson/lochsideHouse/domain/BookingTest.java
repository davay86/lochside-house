package com.davidRonaldson.lochsideHouse.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class BookingTest {

    Booking booking;

    Customer customer;
    Room room;
    Date fromDate;
    Date toDate;

    @Before
    public void setup(){
        customer = new Customer();
        room = new Room();
        fromDate = Date.valueOf("2017-03-02");
        toDate = Date.valueOf("2017-03-15");

        booking = new Booking(customer,room,fromDate,toDate);
        booking.setId(123L);
    }
    @Test
    public void getId() throws Exception {
        Assert.assertEquals(123L,booking.getId());
    }

    @Test
    public void getCustomer() throws Exception {
        Assert.assertEquals(customer,booking.getCustomer());
    }

    @Test
    public void getRoom() throws Exception {
        Assert.assertEquals(room,booking.getRoom());
    }

    @Test
    public void getFromDate() throws Exception {
        Assert.assertEquals(fromDate,booking.getFromDate());
    }

    @Test
    public void getToDate() throws Exception {
        Assert.assertEquals(toDate,booking.getToDate());
    }

}