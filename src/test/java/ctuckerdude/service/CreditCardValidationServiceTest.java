package ctuckerdude.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardValidationServiceTest {

    @Autowired
    private CreditCardValidationService creditCardValidationService;

    @Test
    public void processValidCreditCardNumbersTest() {

       assertEquals("VISA: 4111111111111111 (valid)",
                creditCardValidationService.processCreditCardNumberValidation("4111111111111111"));

       assertEquals("VISA: 4012888888881881 (valid)",
                creditCardValidationService.processCreditCardNumberValidation("4012888888881881"));

       assertEquals("AMEX: 378282246310005 (valid)",
                creditCardValidationService.processCreditCardNumberValidation("378282246310005"));

       assertEquals("Discover: 6011111111111117 (valid)",
                creditCardValidationService.processCreditCardNumberValidation("6011111111111117"));

       assertEquals("MasterCard: 5105105105105100 (valid)",
                creditCardValidationService.processCreditCardNumberValidation("5105105105105100"));
   }

    @Test
    public void processInvalidCreditCardNumbersTest() {

        assertEquals("VISA: 4111111111111 (invalid)",
                creditCardValidationService.processCreditCardNumberValidation("4111111111111"));

        assertEquals("AMEX: 378282246310002 (invalid)",
                creditCardValidationService.processCreditCardNumberValidation("378282246310002"));

        assertEquals("Discover: 6011111111111118 (invalid)",
                creditCardValidationService.processCreditCardNumberValidation("6011111111111118"));

        assertEquals("MasterCard: 5105 1051 0510 5106 (invalid)",
                creditCardValidationService.processCreditCardNumberValidation("5105 1051 0510 5106"));

        assertEquals("Unknown: 9111111111111111 (invalid)",
                creditCardValidationService.processCreditCardNumberValidation("9111111111111111"));
    }

    @Test
    public void processNullCreditCardNumberTest() {
        assertEquals("Unknown: null (invalid)",
                creditCardValidationService.processCreditCardNumberValidation(null));

    }

    @Test
    public void processEmptyCreditCardNumberTest() {
        assertEquals("Unknown:  (invalid)",
                creditCardValidationService.processCreditCardNumberValidation(""));

    }

    @Test
    public void processDodgyCreditCardNumberTest() {
        assertEquals("Unknown: !@#$$^&%^*&^$%^ (invalid)",
                creditCardValidationService.processCreditCardNumberValidation("!@#$$^&%^*&^$%^"));

    }
}
