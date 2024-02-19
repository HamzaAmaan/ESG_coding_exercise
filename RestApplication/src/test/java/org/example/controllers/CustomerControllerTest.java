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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest
{
    private static final String ENDPOINT = "/customer";
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
                    .post(ENDPOINT)
                    .content(asJsonString(customer))
                    .contentType(MediaType.APPLICATION_JSON));
            // THEN
            action.andExpect(status().isCreated());
        }

        @Test
        void returns400_whenProvidedInvalidJson() throws Exception
        {
            // WITH
            // WHEN
            ResultActions action = mockMvc.perform(MockMvcRequestBuilders
                    .post(ENDPOINT)
                    .content("")
                    .contentType(MediaType.APPLICATION_JSON));
            // THEN
            action.andExpect(status().isBadRequest());
        }
    }

    @Nested
    class TestGetCustomer
    {
        @Test
        void returns200AndCustomer_whenProvidedCustomerRef() throws Exception
        {
            // WITH
            final String customerRef = "ref";
            final Customer customer = Customer.builder().customerRef(customerRef).build();
            when(service.getCustomer(eq(customerRef))).thenReturn(customer);
            // WHEN
            ResultActions action = mockMvc.perform(MockMvcRequestBuilders
                    .get(ENDPOINT)
                    .content(customerRef)
                    .contentType(MediaType.APPLICATION_JSON));
            // THEN
            final MvcResult mvcResult = action.andExpect(status().isOk()).andReturn();
            final String contentType = mvcResult.getResponse().getContentType();

            assertEquals("application/json", contentType);
            assertEquals(customer, fromJsonString(mvcResult.getResponse().getContentAsString()));
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

    private Customer fromJsonString(final String json)
    {
        try {
            return new ObjectMapper().readValue(json, Customer.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}