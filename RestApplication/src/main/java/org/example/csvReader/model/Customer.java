package org.example.csvReader.model;

import lombok.*;
import org.example.csvReader.enums.Delimiters;

@AllArgsConstructor
@Data
public class Customer
{
    private String customerRef;
    private String customerName;
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String county;
    private String country;
    private String postcode;

    public static Customer fromLine(final String line)
    {
        String[] fields = line.split(Delimiters.COMMA.getValue());
        // TODO: ADD Validation methods???
        return new Customer(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7]);
    }
}
