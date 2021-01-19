package ctuckerdude.controller;

import ctuckerdude.service.CreditCardValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CreditCardValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardValidationService creditCardValidationService;

    @Test
    public void processCreditCardNumberValidationTest() throws Exception {
        String expectedResponse = "VISA: 4111111111111111 (valid)";

        when(creditCardValidationService.processCreditCardNumberValidation("4111111111111111"))
                .thenReturn(expectedResponse);
        this.mockMvc.perform(get("/api/v1/creditcard/validation/4111111111111111"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResponse)));
    }
}
