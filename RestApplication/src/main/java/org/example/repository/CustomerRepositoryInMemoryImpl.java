package org.example.repository;

import org.example.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepositoryInMemoryImpl implements CustomerRepository
{
    private final Map<String, Customer> database = new HashMap<>();
    @Override
    public void createCustomer(final Customer customer)
    {
        database.put(customer.getCustomerRef(), customer);
    }
}
