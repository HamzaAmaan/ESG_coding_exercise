package org.example;

import org.example.model.Customer;
import org.example.utils.CustomerMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Objects;

import static org.example.enums.ErrorMessage.*;

@SpringBootApplication
@PropertySource(ignoreResourceNotFound = true, value = "classpath:properties/application.properties")
public class CSVReader
{
    private static final String CSV_EXTENSION = ".csv";

    public static void main(String[] args) {
        if (args.length == 0 || Objects.equals(args[0], ""))
        {
            throw new IllegalArgumentException(MISSING_ARGUMENT.getMessage());
        }

        String filePath = args[0];
        if (!filePath.toLowerCase().endsWith(CSV_EXTENSION))
        {
            throw new IllegalArgumentException(INVALID_FILE_EXTENSION.getMessage());
        }

        final File file = new File(filePath);

        if (!file.exists())
        {
            throw new IllegalArgumentException(MISSING_FILE.getMessage());
        }
        final ConfigurableApplicationContext context = SpringApplication.run(CSVReader.class, args);
        final ConfigurableEnvironment environment = context.getEnvironment();
        final String serverPort = environment.getProperty("local.server.port");
        final String apiUrl = String.format("http://localhost:%s/customer", serverPort);
        processCsvFile(file, apiUrl);
    }

    private static void processCsvFile(final File file, final String apiUrl)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String line;
            while ((line = reader.readLine()) != null)
            {
                final Customer customer = CustomerMapper.fromLine(line);
                sendDataToRestAPI(customer,headers, restTemplate, apiUrl);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void sendDataToRestAPI(final Customer customer,
                                  final HttpHeaders headers,
                                  final RestTemplate restTemplate,
                                  final String apiUrl) throws UnknownHostException
    {
        HttpEntity<Customer> request = new HttpEntity<>(customer, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);

        HttpStatusCode statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.CREATED) {
            System.out.println("Customer data sent successfully.");
        } else {
            throw new RuntimeException(FAILED_SENDING_DATA.getMessage() + statusCode);
        }
    }
}