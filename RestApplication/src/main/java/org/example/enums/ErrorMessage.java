package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage
{
    MISSING_ARGUMENT("Please provide the CSV file path as an argument."),
    INVALID_FILE_EXTENSION("The file path provided is not for a CSV file."),
    MISSING_FILE("File does not exist."),
    FAILED_SENDING_DATA("Failed to send customer data. Status code: ")
    ;

    private final String message;
}
