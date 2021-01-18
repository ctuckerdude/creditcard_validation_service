package ctuckerdude.util;

import org.apache.commons.lang3.StringUtils;

public class ValidationUtil {

    // Based on https://gist.github.com/icchan/47d83bacc5113db59fbc
    public static boolean luhnValidation(String value) {
        if (StringUtils.isNotBlank(value)) {
            int digits = value.length();
            int oddOrEven = digits & 1;
            long sum = 0;
            for (int count = 0; count < digits; count++) {
                int digit = 0;
                try {
                    digit = Integer.parseInt(value.charAt(count) + "");
                } catch (NumberFormatException e) {
                    return false;
                }

                if (((count & 1) ^ oddOrEven) == 0) { // not
                    digit *= 2;
                    if (digit > 9) {
                        digit -= 9;
                    }
                }
                sum += digit;
            }

            return (sum == 0) ? false : (sum % 10 == 0);
        } else {
            return false;
        }
    }
}
