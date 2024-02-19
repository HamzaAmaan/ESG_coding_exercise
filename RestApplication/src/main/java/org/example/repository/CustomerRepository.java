package org.example.repository;

import org.example.model.Customer;

public interface CustomerRepository {
    void createCustomer(final Customer customer);

    Customer getCustomer(final String customerRef);
}
