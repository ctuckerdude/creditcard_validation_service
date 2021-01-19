package ctuckerdude.controller;

import ctuckerdude.service.CreditCardValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping("/api/v1/creditcard/validation")
public class CreditCardValidationController {

    private CreditCardValidationService creditCardValidationService;

    public CreditCardValidationController(CreditCardValidationService creditCardValidationService) {
        this.creditCardValidationService = creditCardValidationService;
    }

    @GetMapping(path = "/{ccNumber}", produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<String> processCreditCardNumberValidation(@PathVariable("ccNumber") String ccNumber) {
        return new ResponseEntity<>(creditCardValidationService.processCreditCardNumberValidation(ccNumber),
                HttpStatus.OK);
    }
}
