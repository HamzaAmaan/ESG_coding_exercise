package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Customer;
import org.example.services.CustomerService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest
{
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService service;

    @Nested
    class TestAddCustomer
    {
        @Test
        void returns201_whenCustomerAdded() throws Exception
        {
            // WITH
            final Customer customer = new Customer();
            // WHEN
            ResultActions action = mockMvc.perform(MockMvcRequestBuilders
                    .post("/customer")
                    .content(asJsonString(customer))
                    .contentType(MediaType.APPLICATION_JSON));
            // THEN
            action.andExpect(status().isCreated());
        }
    }

    private String asJsonString(final Customer customer)
    {
        try {
            return new ObjectMapper().writeValueAsString(customer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}