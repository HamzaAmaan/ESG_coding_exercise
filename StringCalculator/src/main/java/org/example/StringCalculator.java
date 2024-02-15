package org.example;

public class StringCalculator {

    private static final String DELIMITER_REGEX = "[,\n]";
    public int Add(final String strNumbers)
    {
        if (strNumbers.isEmpty())
            return 0;
        String[] numbers = strNumbers.split(DELIMITER_REGEX);

        int sum = 0;

        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }

        return sum;
    }
}
