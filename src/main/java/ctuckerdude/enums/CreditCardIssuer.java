package ctuckerdude.enums;

import lombok.Getter;

@Getter
public enum CreditCardIssuer {

    AMEX ( "AMEX"),
    DISCOVER ("Discover"),
    MASTERCARD ( "MasterCard"),
    UNKNOWN("Unknown"),
    VISA ("VISA");


    private String issuerName;

    CreditCardIssuer(String issuerName) {
        this.issuerName = issuerName;
    }

}
