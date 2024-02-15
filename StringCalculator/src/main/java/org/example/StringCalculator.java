package org.example;

public class StringCalculator {

    private static final String DELIMITER_NEW_LINE = "\n";
    private static final String DELIMITER_COMMA = ",";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    public int Add(String strNumbers)
    {
        if (strNumbers.isEmpty())
            return 0;

        String delimiters = DELIMITER_COMMA + DELIMITER_NEW_LINE;

        if (strNumbers.startsWith(CUSTOM_DELIMITER_PREFIX))
        {
            String[] delimiterAndNumbers = strNumbers.split(DELIMITER_NEW_LINE, 2);
            delimiters += delimiterAndNumbers[0].split(CUSTOM_DELIMITER_PREFIX)[1];
            strNumbers = delimiterAndNumbers[1];
        }

        String[] numbers = extractNumbers(strNumbers, delimiters);

        return extractSum(numbers);
    }

    private String[] extractNumbers(String strNumbers, String delimiters) {
        final String regex = "[" + delimiters + "]";
        return strNumbers.split(regex);
    }

    private int extractSum(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {

            sum += Integer.parseInt(number);
        }

        return sum;
    }
}
