package org.example;

import java.io.File;
import java.util.Objects;

public class CSVReader
{
    public static void main(String[] args) {
        if (args.length == 0 || Objects.equals(args[0], "")) {
            throw new IllegalArgumentException("Please provide the CSV file path as an argument.");
        }

        String filePath = args[0];
        if (!filePath.toLowerCase().endsWith(".csv")) {
            throw new IllegalArgumentException("The file path provided is not for a CSV file.");
        }

        File file = new File(filePath);

        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist.");
        }
    }
}