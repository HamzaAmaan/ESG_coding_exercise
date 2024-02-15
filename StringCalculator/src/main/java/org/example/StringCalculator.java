package org.example;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator
{

    private static final String DELIMITER_NEW_LINE = "\n";
    private static final String DELIMITER_COMMA = ",";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String ESCAPE_CHARACTER = "\\";
    private static final String BAR = "|";
    private static final String REGEX_OPEN = "[";
    private static final String REGEX_CLOSE = "]";

    public int Add(String strNumbers)
    {
        if (strNumbers.isEmpty())
            return 0;

        String regex = REGEX_OPEN + DELIMITER_COMMA + DELIMITER_NEW_LINE + REGEX_CLOSE;

        if (strNumbers.startsWith(CUSTOM_DELIMITER_PREFIX))
        {
            String[] delimiterAndNumbers = strNumbers.split(DELIMITER_NEW_LINE, 2);
            regex += extractDelimiters(delimiterAndNumbers[0]);
            strNumbers = delimiterAndNumbers[1];
        }

        String[] numbers = extractNumbers(strNumbers, regex);

        return extractSum(numbers);
    }

    private String extractDelimiters(String strDelimiters)
    {
        strDelimiters = strDelimiters.replace(CUSTOM_DELIMITER_PREFIX, "")
                                                .replace(REGEX_OPEN, "");
        String[] arrDelimiters = strDelimiters.split(REGEX_CLOSE);
        StringBuilder delimiters = new StringBuilder();
        for (String delimiter: arrDelimiters)
        {
            delimiters.append(BAR).append(addEscapeCharacter(delimiter));
        }
        return delimiters.toString();
    }

    private StringBuilder addEscapeCharacter(String delimiter) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < delimiter.length(); i++) {
            sb.append(ESCAPE_CHARACTER).append(delimiter.charAt(i));
        }
        return sb;
    }

    private String[] extractNumbers(String strNumbers, String regex)
    {
        return strNumbers.split(regex);
    }
    private int extractSum(String[] numbers)
    {
        int sum = 0;
        final List<String> invalidNumbers = new ArrayList<>();

        for (String number : numbers)
        {
            int num = Integer.parseInt(number);
            if (num < 0) {
                invalidNumbers.add(number);
            } else {
                if (num <= 1000)
                    sum += num;
            }
        }

        if (!invalidNumbers.isEmpty())
        {
            throw new IllegalArgumentException("Negatives not allowed: " + String.join(DELIMITER_COMMA, invalidNumbers));
        }
        return sum;
    }
}
