package org.example.services;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest
{
    @Mock
    CustomerRepository repository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Nested
    class TestAddCustomer
    {
        @Test
        void callsRepositoryMethodOnce()
        {
            //WITH
            final Customer customer = new Customer();
            //WHEN
            customerService.addCustomer(customer);
            //THEN
            verify(repository, times(1)).createCustomer(customer);
        }
    }
}