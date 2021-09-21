package com.crud.payment.controller;

import com.crud.payment.SpringTest;
import com.crud.payment.service.JwtProviderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class UserPaymentControllerTest extends SpringTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtProviderService jwtProviderService;

    @Test
    public void getAllPaymentsShouldBeForbidden() throws Exception {
        mockMvc.perform(get("/users/1/payments"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getAllPaymentsWithToken() throws Exception {
        String string = jwtProviderService.generateToken("user1");

        mockMvc.perform(get("/users/1/payments")
                        .header("Authorization", "Bearer " + string))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}
