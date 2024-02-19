package org.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.enums.Delimiters;
import org.example.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper
{
    private final ObjectMapper mapper = new ObjectMapper();
    public static Customer fromLine(final String line)
    {
        String[] fields = line.split(Delimiters.COMMA.getValue());
        // TODO: ADD Validation methods???
        return new Customer(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]);
    }
}
