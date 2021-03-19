package com.cloutier.luhnexercise.service;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.ArrayList;

@Component
public class Luhn {

    /**
     * Accepts a card number and determines if the card number is a valid number with
     * respect to the
     * Luhn algorithm.
     *
     * @param cardNumber the card number
     * @return true if the card number is valid according to the Luhn algorithm, false
     * if not
     */
    public boolean isValidLuhn(String cardNumber) {

        // Check for null input
        if (!StringUtils.hasText(cardNumber)) {
            return false;
        }

        // Process card string into an ArrayList
        ArrayList<Integer> cardNumberArray = processCardString(cardNumber);

        // Loop through the array from the right and multiply every second by 2, starting with the second digit
        for (int i = cardNumberArray.size() - 2; i >= 0; i = i - 2) {
            int digit = cardNumberArray.get(i);
            digit = digit * 2;
            if (digit > 9) {
                digit = digit % 10 + 1;
            }
            cardNumberArray.set(i, digit);
        }

        // Calculate the sum of the digits in the Array
        int cardDigitSum = cardNumberArray.stream().mapToInt(Integer::intValue).sum();

        // Check that the remainder of our cardDigitSum divided by 10 is 0
        return cardDigitSum % 10 == 0;
    }

    /**
     * Accepts a partial card number (excluding the last digit) and generates the
     * appropriate Luhn
     * check digit for the number.
     *
     * @param cardNumber the card number (not including a check digit)
     * @return the check digit
     */
    public String generateCheckDigit(String cardNumber) {

        // Check for null input
        if (!StringUtils.hasText(cardNumber)) {
            return null;
        }

        // Process card string into an ArrayList
        ArrayList<Integer> cardNumberArray = processCardString(cardNumber);

        // Loop through array from the right and multiply every other one by two, starting with the first digit
        for (int i = cardNumberArray.size() - 1; i >= 0; i = i - 2) {
            int digit = cardNumberArray.get(i);
            digit = digit * 2;
            if (digit > 9) {
                digit = digit % 10 + 1;
            }
            cardNumberArray.set(i, digit);
        }

        // Calculate the sum of the digits in the Array
        int cardDigitSum = cardNumberArray.stream().mapToInt(Integer::intValue).sum();

        // Multiply the cardDigitSum by 9
        cardDigitSum = cardDigitSum * 9;

        // Get remainder of cardDigitSum divided by 10 and convert to string
        String checkNum = String.valueOf(cardDigitSum % 10);
        return checkNum;
    }

    /**
     * Accepts two card numbers representing the starting and ending numbers of a
     * range of card numbers
     * and counts the number of valid Luhn card numbers that exist in the range,
     * inclusive.
     *
     * @param startRange the starting card number of the range
     * @param endRange   the ending card number of the range
     * @return the number of valid Luhn card numbers in the range, inclusive
     */
    public int countRange(String startRange, String endRange) {

        if (!StringUtils.hasText(startRange) || !StringUtils.hasText(endRange)) {
            return 0;
        }

        // Covert startRange and endRange to Long for processing
        Long startNum = new Long(startRange);
        Long endNum = new Long(endRange);

        // Set counter to the difference between start and end randge
        Long counter = endNum - startNum;

        // Initialize number of valid cards to 0
        int numValid = 0;

        // Loop using counter, check for valid card by using isValidLuhn method, if valid iterate numValid by 1,
        // iterate the checked number by 1, and iterate the counter down by 1
        while (counter >= 0) {
            boolean isValid = isValidLuhn(startNum.toString());
            if (isValid) {
                numValid += 1;
            }
            startNum = startNum + 1;
            counter -= 1;
        }
        return numValid;
    }


    /**
     * Helper method to convert card number string into an array for further processing and avoid unneeded code duplication
     */
    public ArrayList<Integer> processCardString(String cardNumberStr) {

        // Initialize empty array list
        ArrayList<Integer> cardArray = new ArrayList<>();

        // loop through the string
        for (int i = 0; i < cardNumberStr.length(); i++) {
            cardArray.add(Integer.parseInt(cardNumberStr.substring(i, i + 1)));
        }
        return cardArray;
    }

}

