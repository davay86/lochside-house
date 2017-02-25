package com.davidRonaldson.lochsideHouse.integration;

import com.davidRonaldson.lochsideHouse.app.LochsideHouseBookingService;
import com.davidRonaldson.lochsideHouse.domain.Booking;
import com.davidRonaldson.lochsideHouse.repositories.BookingRepo;
import com.davidRonaldson.lochsideHouse.repositories.RoomRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LochsideHouseBookingService.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BookingIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private RoomRepo roomRepo;


    @Before
    public void setup(){
    }


    @Test
    public void getRoomBookings(){
        String expectedResponse = "[{\"id\":3,\"customer\":{\"id\":102,\"name\":\"Ashley\"},\"room\":{\"id\":1001,\"type\":\"Single\",\"price\":100.0},\"fromDate\":\"2017-10-10\",\"toDate\":\"2017-10-19\"}]";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/roomBookings")
                .queryParam("room", "1001");

        String response = testRestTemplate.getForObject(
                builder.build().encode().toUri(),String.class);

        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void getCustomerBookings(){
        String expectedResponse = "[{\"id\":1,\"customer\":{\"id\":101,\"name\":\"David\"},\"room\":{\"id\":1002,\"type\":\"Single\",\"price\":100.0},\"fromDate\":\"2017-12-17\",\"toDate\":\"2017-12-19\"},{\"id\":4,\"customer\":{\"id\":101,\"name\":\"David\"},\"room\":{\"id\":1005,\"type\":\"Double\",\"price\":200.0},\"fromDate\":\"2017-09-12\",\"toDate\":\"2017-09-04\"}]";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/customerBookings")
                .queryParam("customer", "101");

        String response = testRestTemplate.getForObject(
                builder.build().encode().toUri(),String.class);

        Assert.assertEquals(expectedResponse,response);
    }

    @Test
    public void getAvailability_roomAvailable(){
        String expectedResponse = "Room available";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/availability")
                .queryParam("room", "1001")
                .queryParam("from","2019-09-10")
                .queryParam("to","2019-09-03");

        String response = testRestTemplate.getForObject(
                builder.build().encode().toUri(),String.class);

        Assert.assertEquals(expectedResponse,response);
    }

    @Test
    public void getAvailability_roomUnavailable(){
        String expectedResponse = "Date unavailable";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/availability")
                .queryParam("room", "1001")
                .queryParam("from","2017-10-11")
                .queryParam("to","2017-10-20");

        String response = testRestTemplate.getForObject(
                builder.build().encode().toUri(),String.class);

        Assert.assertEquals(expectedResponse,response);
    }

    @Test
    public void createBooking(){
        int noOfBookingsBeforeCreation = bookingRepo.findAll().size();


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/createBooking")
                .queryParam("room", "1003")
                .queryParam("customer", "101")
                .queryParam("fromDate", "2017-05-27")
                .queryParam("toDate", "2017-06-06");


        testRestTemplate.postForLocation(
                builder.build().encode().toUri(),String.class);

        List<Booking> bookings = bookingRepo.findByRoom(roomRepo.findOne(1003L));

        Assert.assertEquals(noOfBookingsBeforeCreation + 1, bookingRepo.findAll().size());
        Assert.assertEquals(1,bookings.size());
        Assert.assertEquals(101L,bookings.get(0).getCustomer().getId());
        Assert.assertEquals(1003L,bookings.get(0).getRoom().getId());
        Assert.assertEquals("2017-05-27",bookings.get(0).getFromDate().toString());
        Assert.assertEquals("2017-06-06",bookings.get(0).getToDate().toString());

        bookingRepo.delete(bookings.get(0));
    }

}
