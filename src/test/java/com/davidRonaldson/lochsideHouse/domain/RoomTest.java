package com.davidRonaldson.lochsideHouse.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoomTest {

    Room room;
    Booking booking;


    @Before
    public void setUp() throws Exception {
        room = new Room("Single",100);
        room.setId(123L);
    }

    @Test
    public void getId() throws Exception {
        Assert.assertEquals(123L,room.getId());
    }

    @Test
    public void getPrice() throws Exception {
        Assert.assertTrue(100 == room.getPrice());
    }

    @Test
    public void getType() throws Exception {
        Assert.assertEquals("Single", room.getType());
    }

}