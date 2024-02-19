package org.example.controllers;

import org.example.model.Customer;
import org.example.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    private CustomerService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer)
    {
        service.addCustomer(customer);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@RequestBody String customerRef)
    {
        return service.getCustomer(customerRef);
    }
}
