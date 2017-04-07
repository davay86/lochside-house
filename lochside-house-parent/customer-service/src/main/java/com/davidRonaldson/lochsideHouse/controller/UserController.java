package com.davidRonaldson.lochsideHouse.controller;

import com.davidRonaldson.lochsideHouse.domain.Customer;
import com.davidRonaldson.lochsideHouse.domain.CustomerAddress;
import com.davidRonaldson.lochsideHouse.repositories.CustomerAddressRepo;
import com.davidRonaldson.lochsideHouse.repositories.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CustomerAddressRepo customerAddressRepo;

    @RequestMapping(value = "/allCustomers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @RequestMapping(value = "/customerDetails", method = RequestMethod.GET)
    public Customer getCustomerDetails(@RequestParam(value = "customer_id") String id) {
        return customerRepo.findOne(Long.valueOf(id));
    }

    @RequestMapping(value = "/registerCustomer", method = RequestMethod.POST)
    public void registerCustomer(@RequestParam(value = "firstName") String firstName, @RequestParam(value = "surname") String surname,
                                 @RequestParam(value = "houseNumber") String houseNumber, @RequestParam(value = "AdrLineOne") String adrLineOne,
                                 @RequestParam(value = "adrLineTwo") String adrLineTwo, @RequestParam(value = "postcode") String postcode, @RequestParam(value = "email") String email) {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setHouseNumber(houseNumber);
        customerAddress.setAddressLineOne(adrLineOne);
        customerAddress.setAddressLineTwo(adrLineTwo);
        customerAddress.setPostcode(postcode);

        customerAddressRepo.save(customerAddress);

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setSurname(surname);
        customer.setRegistrationDate(new Date());
        customer.setEmail(email);
        customer.setCustomerAddress(customerAddress);

        customerRepo.save(customer);
    }
}
