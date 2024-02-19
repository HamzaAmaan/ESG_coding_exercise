package org.example;

import org.example.model.Customer;
import org.example.utils.CustomerMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import static org.example.enums.ErrorMessage.*;

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

        processCsvFile(file);
    }

    private static void processCsvFile(final File file)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                final Customer customer = CustomerMapper.fromLine(line);
                sendDataToRestAPI(customer);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void sendDataToRestAPI(final Customer customer)
    {
    }
}