package com.davidRonaldson.lochsideHouse.integration;

import com.davidRonaldson.lochsideHouse.app.LochsideHouseBookingService;
import com.davidRonaldson.lochsideHouse.domain.Booking;
import com.davidRonaldson.lochsideHouse.repositories.BookingRepo;
import com.davidRonaldson.lochsideHouse.repositories.RoomRepo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

        HttpEntity<String> request = setupAuthenticatedUser();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/lochsideHouse/roomBookings")
                .queryParam("room", "1001");

        ResponseEntity<String> response = testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, String.class);

        Assert.assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void getRoomBookings_notAuthenticated() {

        String expectedResponse = "[{\"id\":3,\"customer\":{\"id\":102,\"name\":\"Ashley\"},\"room\":{\"id\":1001,\"type\":\"Single\",\"price\":100.0},\"fromDate\":\"2017-10-10\",\"toDate\":\"2017-10-19\"}]";

        HttpEntity<String> request = setupNonAuthenticatedUser();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/lochsideHouse/roomBookings")
                .queryParam("room", "1001");

        ResponseEntity<String> response = testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, String.class);

        Assert.assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    public void getCustomerBookings(){
        String expectedResponse = "[{\"id\":1,\"customer\":{\"id\":101,\"name\":\"David\"},\"room\":{\"id\":1002,\"type\":\"Single\",\"price\":100.0},\"fromDate\":\"2017-12-17\",\"toDate\":\"2017-12-19\"},{\"id\":4,\"customer\":{\"id\":101,\"name\":\"David\"},\"room\":{\"id\":1005,\"type\":\"Double\",\"price\":200.0},\"fromDate\":\"2017-09-12\",\"toDate\":\"2017-09-04\"}]";

        HttpEntity<String> request = setupAuthenticatedUser();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/lochsideHouse/customerBookings")
                .queryParam("customer", "101");

        ResponseEntity<String> response = testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, String.class);

        Assert.assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void getCustomerBookings_notAuthenticated() {
        String expectedResponse = "[{\"id\":1,\"customer\":{\"id\":101,\"name\":\"David\"},\"room\":{\"id\":1002,\"type\":\"Single\",\"price\":100.0},\"fromDate\":\"2017-12-17\",\"toDate\":\"2017-12-19\"},{\"id\":4,\"customer\":{\"id\":101,\"name\":\"David\"},\"room\":{\"id\":1005,\"type\":\"Double\",\"price\":200.0},\"fromDate\":\"2017-09-12\",\"toDate\":\"2017-09-04\"}]";

        HttpEntity<String> request = setupNonAuthenticatedUser();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/lochsideHouse/customerBookings")
                .queryParam("customer", "101");

        ResponseEntity<String> response = testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, String.class);

        Assert.assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    public void getAvailability_roomAvailable(){
        String expectedResponse = "Room available";

        HttpEntity<String> request = setupAuthenticatedUser();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/lochsideHouse/availability")
                .queryParam("room", "1001")
                .queryParam("from","2019-09-10")
                .queryParam("to","2019-09-03");

        ResponseEntity<String> response = testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, String.class);

        Assert.assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void getAvailability_roomUnavailable(){
        String expectedResponse = "Date unavailable";

        HttpEntity<String> request = setupAuthenticatedUser();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/lochsideHouse/availability")
                .queryParam("room", "1001")
                .queryParam("from","2017-10-11")
                .queryParam("to","2017-10-20");

        ResponseEntity<String> response = testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, String.class);

        Assert.assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void getAvailability_notAuthenticated() {
        String expectedResponse = "Date unavailable";

        HttpEntity<String> request = setupNonAuthenticatedUser();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/lochsideHouse/availability")
                .queryParam("room", "1001")
                .queryParam("from", "2017-10-11")
                .queryParam("to", "2017-10-20");

        ResponseEntity<String> response = testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, request, String.class);

        Assert.assertEquals(403, response.getStatusCodeValue());
    }

    @Test
    public void createBooking(){
        int noOfBookingsBeforeCreation = bookingRepo.findAll().size();

        HttpEntity<String> request = setupAuthenticatedUser();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/lochsideHouse/createBooking")
                .queryParam("room", "1003")
                .queryParam("customer", "101")
                .queryParam("fromDate", "2017-05-27")
                .queryParam("toDate", "2017-06-06");


        testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, request, String.class);

        List<Booking> bookings = bookingRepo.findByRoom(roomRepo.findOne(1003L));

        Assert.assertEquals(noOfBookingsBeforeCreation + 1, bookingRepo.findAll().size());
        Assert.assertEquals(1,bookings.size());
        Assert.assertEquals(101L,bookings.get(0).getCustomer().getId());
        Assert.assertEquals(1003L,bookings.get(0).getRoom().getId());
        Assert.assertEquals("2017-05-27",bookings.get(0).getFromDate().toString());
        Assert.assertEquals("2017-06-06",bookings.get(0).getToDate().toString());

        bookingRepo.delete(bookings.get(0));
    }

    @Test
    public void createBooking_notAuthenticated() {
        int noOfBookingsBeforeCreation = bookingRepo.findAll().size();

        HttpEntity<String> request = setupNonAuthenticatedUser();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/lochsideHouse/createBooking")
                .queryParam("room", "1003")
                .queryParam("customer", "101")
                .queryParam("fromDate", "2017-05-27")
                .queryParam("toDate", "2017-06-06");


        testRestTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, request, String.class);

        List<Booking> bookings = bookingRepo.findByRoom(roomRepo.findOne(1003L));

        Assert.assertEquals(noOfBookingsBeforeCreation, bookingRepo.findAll().size());
        Assert.assertEquals(0, bookings.size());
    }

    private HttpEntity<String> setupAuthenticatedUser() {
        String credentials = "david:password";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedCredentials);

        return new HttpEntity<String>(headers);
    }

    private HttpEntity<String> setupNonAuthenticatedUser() {
        String credentials = "ashley:password";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedCredentials);

        return new HttpEntity<String>(headers);
    }
}
