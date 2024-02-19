package org.example.services;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService
{
    @Autowired
    private CustomerRepository repository;

    @Override
    public void addCustomer(final Customer customer)
    {
        repository.createCustomer(customer);
    }
}
