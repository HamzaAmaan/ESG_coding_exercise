package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest
{

    final StringCalculator calculator = new StringCalculator();
    @Nested
    class TestAdd {
        @Test
        void returns0_whenGivenEmptyString()
        {
            // WITH
            final String numbers = "";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(0, result);
        }

        @Test
        void returnsSameNumber_whenGivenStringContaining1Number()
        {
            // WITH
            final String numbers = "5";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(5, result);
        }

        @Test
        void returnsSum_whenGivenStringContaining2Numbers()
        {
            // WITH
            final String numbers = "1,2";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(3, result);
        }


        @Test
        void returnsSum_whenGivenStringContainingMoreThan2Numbers()
        {
            // WITH
            final String numbers = "1,2,5,13";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(21, result);
        }

        @Test
        void returnsSum_whenSplitUsingNewLine()
        {
            // WITH
            final String numbers = "1\n2,3";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(6, result);
        }

        @Test
        void returnsSum_whenSplitUsingCustomDelimiter()
        {
            // WITH
            final String numbers = "//;\n1;2";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(3, result);
        }

        @Test
        void throwsException_whenStringContains1NegativeNumber()
        {
            // WITH
            final String numbers = "-1,2";
            final String expectedMessage = "Negatives not allowed: -1";
            // WHEN
            final IllegalArgumentException exception
                    = assertThrows(IllegalArgumentException.class, () -> calculator.Add(numbers));
            // THEN
            assertEquals(expectedMessage, exception.getMessage());
        }

        @Test
        void throwsException_whenStringContainsMultipleNegativeNumbers()
        {
            // WITH
            final String numbers = "2,-4,3,-5";
            final String expectedMessage = "Negatives not allowed: -4,-5";
            // WHEN
            final IllegalArgumentException exception
                    = assertThrows(IllegalArgumentException.class, () -> calculator.Add(numbers));
            // THEN
            assertEquals(expectedMessage, exception.getMessage());
        }

        @Test
        void returnsSum_ignoresValuesAbove1000()
        {
            // WITH
            final String numbers = "1001,2";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(2, result);
        }

        @Test
        void returnsSum_whenAnyLengthCustomDelimiters()
        {
            // WITH
            final String numbers = "//[|||]\n1|||2|||3";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(6, result);
        }

        @Test
        void returnsSum_whenMultipleCustomDelimiters()
        {
            // WITH
            final String numbers = "//[|][%]\n1|2%3";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(6, result);
        }

        @Test
        void returnsSum_whenMultipleAnyLengthCustomDelimiters()
        {
            // WITH
            final String numbers = "//[|||][%%%]\n1|||2%%%3";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(6, result);
        }
    }
}