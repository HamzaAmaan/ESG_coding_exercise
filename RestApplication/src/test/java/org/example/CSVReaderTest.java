package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVReaderTest
{
    @Nested
    class TestRetrievingFile {
        @Test
        void throwsException_whenNoFilePathPassedAsArgument()
        {
            // WITH
            final String expectedMessage = "Please provide the CSV file path as an argument.";
            // WHEN
            final IllegalArgumentException exception
                    = assertThrows(IllegalArgumentException.class, () -> CSVReader.main(new String[0]));
            // THEN
            assertEquals(expectedMessage, exception.getMessage());
        }

        @Test
        void throwsException_whenEmptyStringPassedForFilePath()
        {
            // WITH
            final String filePath = "";
            final String expectedMessage = "Please provide the CSV file path as an argument.";
            // WHEN
            final IllegalArgumentException exception
                    = assertThrows(IllegalArgumentException.class, () -> CSVReader.main(new String[]{filePath}));
            // THEN
            assertEquals(expectedMessage, exception.getMessage());
        }

        @Test
        void throwsException_whenPassedFilePathNotEndingInCSV()
        {
            // WITH
            final String filePath = "./src/test/resources/file";
            final String expectedMessage = "The file path provided is not for a CSV file.";
            // WHEN
            final IllegalArgumentException exception
                    = assertThrows(IllegalArgumentException.class, () -> CSVReader.main(new String[]{filePath}));
            // THEN
            assertEquals(expectedMessage, exception.getMessage());
        }

        @Test
        void throwsException_whenFileDoesNotExist()
        {
            // WITH
            final String filePath = "./src/test/resources/invalidFile.csv";
            final String expectedMessage = "File does not exist.";
            // WHEN
            final IllegalArgumentException exception
                    = assertThrows(IllegalArgumentException.class, () -> CSVReader.main(new String[]{filePath}));
            // THEN
            assertEquals(expectedMessage, exception.getMessage());
        }
    }
}