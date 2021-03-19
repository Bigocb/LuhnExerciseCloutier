package com.cloutier.luhnexercise.service;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;


import static org.junit.jupiter.api.Assertions.*;


class LuhnTest {

    private Luhn luhn = new Luhn();

    /* Tests for isCardValidMethod */

    /* Positive Scenario: Valid card number is submitted */
    @Test
    void test_isValidLuhn() {
            String cardNumber = "1358954993914435";
            boolean isCardValid = luhn.isValidLuhn(cardNumber);
            assertTrue(isCardValid);
    }

    /* Negative Scenario: Invalid card number is submitted */
    @Test
    void test_isInvalidLuhn(){
        String cardNumber = "1358954993914434";
        boolean isCardValid = luhn.isValidLuhn(cardNumber);
        assertFalse(isCardValid);
    }

    /* Negative Scenario: Empty card number is submitted */
    @Test
    void test_nullString(){
        String cardNumber = "";
        boolean isCardValid = luhn.isValidLuhn(cardNumber);
        assertFalse(isCardValid);
    }

    /* Tests for generateCheckDigit */

    /* Positive Scenario: Partial card is submitted and correct check number is returned */
    @Test
    void test_partialCardCorrectCheckNumber() {
        String cardNumber = "135895499391443";
        String checkNumber = luhn.generateCheckDigit(cardNumber);
        assertEquals("5",checkNumber);
    }

    /* Negative Scenario: Empty card number is submitted */
    @Test
    void test_emptyCardNoCheckNumber() {
        String cardNumber = "";
        String checkNumber = luhn.generateCheckDigit(cardNumber);
        assertNull(checkNumber);
    }

    /* Tests for countRange */

    /* Card range is submitted and one valid value is found */
    @Test
    void test_countRangeOneValid() {
        String startCardNumber = "1358954993914434";
        String endCardNumber = "1358954993914436";
        int validCards = luhn.countRange(startCardNumber,endCardNumber);
        assertEquals(1,validCards);
    }

    /* One card provided is an empty string */
    @Test
    void test_countRangeStartIsEmpty() {
        String startCardNumber = "";
        String endCardNumber = "1358954993914436";
        int validCards = luhn.countRange(startCardNumber,endCardNumber);
        assertEquals(0,validCards);
    }


    @Test
    void processCardString() {
    }
}
