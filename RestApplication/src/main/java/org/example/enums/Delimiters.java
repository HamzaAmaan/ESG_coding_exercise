package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Delimiters
{
    COMMA(",");
    private final String value;
}
