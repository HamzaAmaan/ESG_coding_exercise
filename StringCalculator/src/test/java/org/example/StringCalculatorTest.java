package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorTest {

    final StringCalculator calculator = new StringCalculator();
    @Nested
    class TestAdd {
        @Test
        void returns0_whenGivenEmptyString() {
            // WITH
            final String numbers = "";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(0, result);
        }

        @Test
        void returnsSameNumber_whenGivenStringContaining1Number() {

            // WITH
            final String numbers = "5";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(5, result);
        }

        @Test
        void returnsSum_whenGivenStringContaining2Numbers() {

            // WITH
            final String numbers = "1,2";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(3, result);
        }


        @Test
        void returnsSum_whenGivenStringContainingMoreThan2Numbers() {

            // WITH
            final String numbers = "1,2,5,13";
            // WHEN
            final int result = calculator.Add(numbers);
            // THEN
            assertEquals(21, result);
        }
    }

}