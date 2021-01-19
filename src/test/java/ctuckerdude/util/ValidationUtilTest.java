package ctuckerdude.util;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationUtilTest {

    @Test
    public void luhnValidationTestWithValidNumbers() {
        assertTrue(ValidationUtil.luhnValidation("4111111111111111"));
        assertTrue(ValidationUtil.luhnValidation("4012888888881881"));
        assertTrue(ValidationUtil.luhnValidation("378282246310005"));
        assertTrue(ValidationUtil.luhnValidation("6011111111111117"));
        assertTrue(ValidationUtil.luhnValidation("5105105105105100"));
    }

    @Test
    public void luhnValidationTestWithInvalidNumbers() {
        assertFalse(ValidationUtil.luhnValidation("4111111111111"));
        assertFalse(ValidationUtil.luhnValidation("378282246310002"));
        assertFalse(ValidationUtil.luhnValidation("6011111111111118"));
        assertFalse(ValidationUtil.luhnValidation("9111111111111111"));
    }

    @Test
    public void luhnValidationTestWithNull() {
        assertFalse(ValidationUtil.luhnValidation(null));
    }

    @Test
    public void luhnValidationTestWithEmpty() {
        assertFalse(ValidationUtil.luhnValidation(""));
    }

    @Test
    public void luhnValidationTestWithDodgyNumber() {
        assertFalse(ValidationUtil.luhnValidation("!@#$$^*%^&*()"));
    }
}
