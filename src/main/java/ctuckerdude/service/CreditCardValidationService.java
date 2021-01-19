package ctuckerdude.service;

import ctuckerdude.enums.CreditCardIssuer;
import ctuckerdude.util.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CreditCardValidationService {

    // Regex are based on https://gist.github.com/icchan/47d83bacc5113db59fbc
    public static final String AMAX_VALIDATION_REGEX = "^3[47][0-9]{13}$";
    public static final String DISCOVER_VALIDATION_REGEX = "^6(?:011|5[0-9]{2})[0-9]{12}$";
    public static final String MASTERCARD_VALIDATION_REGEX = "^5[1-5][0-9]{14}$";
    public static final String VISA_VALIDATION_REGEX = "^4[0-9]{12}(?:[0-9]{3})?$";

    public static final String INVALID = "invalid";
    public static final String VALID = "valid";

    private static final String CREDIT_CARD_VALIDATION_RESPONSE_FORMAT = "%s: %s (%s)";

    public static Map<String, String> ISSUER_VALIDATION_REGEX_MAP = Stream.of(
            new String[]{CreditCardIssuer.AMEX.name(), AMAX_VALIDATION_REGEX},
            new String[]{CreditCardIssuer.DISCOVER.name(), DISCOVER_VALIDATION_REGEX},
            new String[]{CreditCardIssuer.MASTERCARD.name(), MASTERCARD_VALIDATION_REGEX},
            new String[]{CreditCardIssuer.VISA.name(), VISA_VALIDATION_REGEX}
    ).collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));


    public CreditCardValidationService() {
    }

    public String findIssuerName(String ccNumber) {
        CreditCardIssuer creditCardIssuer = StringUtils.isNotBlank(ccNumber)
                ? Arrays.asList(CreditCardIssuer.values()).stream()
                .filter(ccIssuer -> !CreditCardIssuer.UNKNOWN.equals(ccIssuer)
                        && ccNumber.matches(ISSUER_VALIDATION_REGEX_MAP.get(ccIssuer.name())))
                   .findFirst()
                   .orElse(CreditCardIssuer.UNKNOWN)
                : CreditCardIssuer.UNKNOWN;
        return creditCardIssuer.getIssuerName();
    }


    private String creditCardNumberValidation(String ccNumber) {
       return ValidationUtil.luhnValidation(ccNumber) ? VALID : INVALID;
    }

    public String processCreditCardNumberValidation(String ccNumber) {
      // Basic sanitising whitespace only, used to determine issuer, validation will be performed on
      // ccNumber.
      String sanitisedCcNumber = StringUtils.deleteWhitespace(ccNumber);
      String issuerName = findIssuerName(sanitisedCcNumber);
      String ccNumberValidation = StringUtils.equals(CreditCardIssuer.UNKNOWN.getIssuerName(), issuerName)
              ? INVALID
              : creditCardNumberValidation(ccNumber);
      return String.format(CREDIT_CARD_VALIDATION_RESPONSE_FORMAT, issuerName, ccNumber, ccNumberValidation);
    }

}
