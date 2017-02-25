package com.davidRonaldson.lochsideHouse.domain;

import com.davidRonaldson.lochsideHouse.domain.Booking;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMERS")
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "NAME")
    private String name;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
