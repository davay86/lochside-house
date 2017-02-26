package com.davidRonaldson.lochsideHouse.repositories;

import com.davidRonaldson.lochsideHouse.config.RepositoryConfig;
import com.davidRonaldson.lochsideHouse.domain.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class})
public class CustomerRepoTest {

    @Autowired
    CustomerRepo customerRepo;

    Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("name");
        customerRepo.save(customer);
    }

    @After
    public void teardown(){
        customerRepo.delete(customer);
    }

    @Test
    public void findAll(){
        Assert.assertEquals(1,customerRepo.findAll().size());
    }

    @Test
    public void findOne(){
        Customer foundCustomer = customerRepo.findOne(customer.getId());
        Assert.assertEquals(customer.getName(), foundCustomer.getName());
        Assert.assertEquals(customer.getId(), foundCustomer.getId());
    }

}