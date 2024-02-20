# CSVReader Application

## Overview:
The CSVReader application is designed to read data from a CSV file, map it to a `Customer` object, and send the data to a REST API.

## Usage:
1. **Running the Application:**
    - Compile and run the `CSVReader` class.
    - Pass the path to the CSV file as a command-line argument.

2. **How It Works:**
    - The application verifies the command-line argument for the CSV file path.
    - It checks if the file exists and has a `.csv` extension.
    - Reads each line from the CSV file, maps it to a `Customer` object using `CustomerMapper`, and sends the data to a REST API.

3. **Error Handling:**
    - The application throws exceptions for missing arguments, invalid file extensions, and missing files.

## Why It's Done Like This:
- **Spring Boot Application:** Uses Spring Boot for easy setup and configuration of the application.
- **REST API Integration:** Utilizes `RestTemplate` for making HTTP requests to the REST API.
- **Exception Handling:** Catches and prints `IOException` during file processing and `UnknownHostException` during REST API calls.
- **Property Configuration:** Loads properties from `application.properties` file using `@PropertySource`.
- **Command-Line Arguments:** Checks and processes command-line arguments for the CSV file path.

## Improvements:
- **Value Validation:** Implement validation for CSV file values to ensure data integrity before mapping to the `Customer` object.
- **Database Implementation:** Add a database implementation to replace the current in-memory database for persistent storage of `Customer` data.

## Example Command:

java -jar CSVReader.jar /path/to/file.csv

## Note:
- Ensure that the REST API server is running on `http://localhost:<port>/customer`.
  - A port is randomly chosen. To use a specific port add `server.port=<port>` to the application.properties file in the resources folder.
- Customize the `CustomerMapper` class to map CSV data to the `Customer` object according to your requirements.
