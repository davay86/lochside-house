package com.davidRonaldson.lochsideHouse.repositories;

import com.davidRonaldson.lochsideHouse.config.RepositoryConfig;
import com.davidRonaldson.lochsideHouse.domain.Booking;
import com.davidRonaldson.lochsideHouse.domain.Room;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class RoomRepoTest {

    @Autowired
    RoomRepo roomRepo;

    Room room;

    @Before
    public void setUp() throws Exception {
        room = new Room("Single",100);
        roomRepo.save(room);
    }

    @After
    public void tearDown() throws Exception {
        roomRepo.delete(room);
    }

    @Test
    public void findAll(){
        List<Room> foundRooms = roomRepo.findAll();
        Assert.assertEquals(1,foundRooms.size());
    }

    @Test
    public void findOne(){
        Room foundRoom = roomRepo.findOne(room.getId());

        Assert.assertEquals(room.getId(),foundRoom.getId());
        Assert.assertEquals(room.getType(),foundRoom.getType());
        Assert.assertTrue(room.getPrice() == foundRoom.getPrice());
    }

}