package org.example.model;

import lombok.*;
import org.example.enums.Delimiters;

@AllArgsConstructor
@NoArgsConstructor
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
}
