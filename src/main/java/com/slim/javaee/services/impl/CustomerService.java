package com.slim.javaee.services.impl;

import com.slim.javaee.core.repository.ICustomerRepository;
import com.slim.javaee.services.ICustomerService;

import javax.inject.Inject;

/**
 * {@inheritDoc}
 */
public class CustomerService implements ICustomerService {

    private ICustomerRepository customerRepository;

    @Inject
    public CustomerService(ICustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
}
