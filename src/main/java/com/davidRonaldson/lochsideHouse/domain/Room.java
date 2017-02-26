package com.davidRonaldson.lochsideHouse.domain;

import javax.persistence.*;

@Entity
@Table(name="ROOMS")
public class Room {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "ROOM_TYPE")
    private String type;

    @Column(name = "PRICE")
    private double price;

    public Room() {
    }

    public Room(String type, float price) {
        this.type = type;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
