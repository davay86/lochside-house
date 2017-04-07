package com.davidRonaldson.lochsideHouse.domain;

import javax.persistence.*;

@Entity
@Table(name = "CUSTOMER_ADDRESS")
public class CustomerAddress {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLineOne;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLineTwo;

    @Column(name = "POSTCODE")
    private String postcode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
