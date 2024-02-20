package org.example;

import org.example.model.Customer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CSVReaderTest
{
    @Nested
    class TestRetrievingFile
    {
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

    @Nested
    @ExtendWith(MockitoExtension.class)
    class TestSendingDataToApi
    {
        @Mock
        private RestTemplate restTemplate;
        @Test
        public void successfullySendsData() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
        {
            // WITH
            final Customer customer = new Customer();
            final String apiUrl = "http://localhost:8080/customer";
            final HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            when(restTemplate.postForEntity(anyString(), any(HttpEntity.class), any())).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

            // Access method using reflection
            Method sendDataToRestAPIMethod = CSVReader.class.getDeclaredMethod("sendDataToRestAPI",
                    Customer.class, HttpHeaders.class, RestTemplate.class, String.class);
            sendDataToRestAPIMethod.setAccessible(true);
            // WHEN
            sendDataToRestAPIMethod.invoke(null, customer, headers, restTemplate, apiUrl);
            // THEN
            verify(restTemplate, times(1)).postForEntity(eq(apiUrl), any(), eq(String.class));
        }
    }
}