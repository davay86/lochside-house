package com.davidRonaldson.lochsideHouse.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CustomerTest {

    Customer customer;
    Booking booking;

    @Before
    public void setUp() throws Exception {
        customer = new Customer();
        customer.setId(123L);
        customer.setName("Test Customer");
    }

    @Test
    public void getId() throws Exception {
        Assert.assertEquals(123L,customer.getId());
    }

    @Test
    public void getName() throws Exception {
        Assert.assertEquals("Test Customer",customer.getName());
    }

}