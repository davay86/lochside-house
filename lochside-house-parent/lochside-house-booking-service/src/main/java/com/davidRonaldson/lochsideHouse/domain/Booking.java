package com.davidRonaldson.lochsideHouse.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="BOOKINGS")
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Room room;

    @Column(name="FROM_DATE")
    private Date fromDate;

    @Column(name="TO_DATE")
    private Date toDate;

    public Booking() {
    }

    public Booking(Customer customer, Room room, Date fromDate, Date toDate) {
        this.customer = customer;
        this.room = room;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

}
