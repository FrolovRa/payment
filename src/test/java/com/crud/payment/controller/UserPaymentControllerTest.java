package com.crud.payment.controller;

import com.crud.payment.SpringTest;
import com.crud.payment.domain.User;
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

    @Test
    public void getAllPaymentsShouldBeForbidden() throws Exception {
        mockMvc.perform(get("/users/1/payments"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getAllPaymentsWithToken() throws Exception {
        User user = new User();
        user.setName("user1");
        user.setPassword("password");
        String token = jwtProviderService.generateToken(user);

        mockMvc.perform(get("/users/1/payments")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty());
    }
}
