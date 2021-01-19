package ctuckerdude.util;

import org.apache.commons.lang3.StringUtils;

public class ValidationUtil {

    public static boolean luhnValidation(String value) {
        if (StringUtils.isNotBlank(value)) {
            String luhnString = StringUtils.EMPTY;
            String valueReverse = StringUtils.reverse(value);

            for (int i = 0; i < valueReverse.length(); i++) {
                luhnString += (i % 2) == 0
                        ? String.valueOf(valueReverse.charAt(i))
                        : String.valueOf(Character.getNumericValue(valueReverse.charAt(i)) * 2);
            }

            int sum = 0;
            for (int i = 0; i < luhnString.toCharArray().length; i++) {
                sum += Character.getNumericValue(luhnString.charAt(i));
            }

            return (int) sum % 10 == 0;
        } else {
            return false;
        }
    }
}
