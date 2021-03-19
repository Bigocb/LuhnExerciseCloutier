package com.cloutier.luhnexercise.startup;

import com.cloutier.luhnexercise.service.Luhn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartup  implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(AppStartup.class);

    @Value( "${le.cardNumber}" )
    private String cardNumber;

    @Value( "${le.partialCardNumber}" )
    private String partialCardNumber;

    @Value( "${le.startOfCardRange}" )
    private String startOfRange;

    @Value( "${le.endOfCardRange}" )
    private String endOfRange;

    @Autowired
    private Luhn luhn;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // First lets test for a valid card number
        String valid = luhn.isValidLuhn(cardNumber) ? "valid" : "invalid";
        LOG.info("This card number is " + valid + ".");

        // Next lets calculate the check num for an partial number
        String checkNumber = luhn.generateCheckDigit(partialCardNumber);
        LOG.info("This check number for this partial card is: " + checkNumber);

        // finally lets take a range of card numbers and find the number of valid cards within it
        int validNumberOfCards = luhn.countRange(startOfRange, endOfRange);
        LOG.info("The number of valid card numbers within this range is: " + validNumberOfCards);

    }

}
