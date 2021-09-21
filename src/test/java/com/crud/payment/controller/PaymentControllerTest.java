package com.crud.payment.controller;

import com.crud.payment.SpringTest;
import com.crud.payment.domain.User;
import com.crud.payment.dto.payment.PaymentCreateDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class PaymentControllerTest extends SpringTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Test
    public void validatePaymentForPaymentTypeOne() throws Exception {
        PaymentCreateDto dto = new PaymentCreateDto();
        dto.setAmount("100");
        dto.setCreditorIbanId(1L);
        dto.setDebtorIbanId(2L);
        dto.setCurrencyId(1L);
        dto.setPaymentTypeId(1L);

        User user = new User();
        user.setName("user1");
        user.setPassword("password");
        String token = jwtProviderService.generateToken(user);

        mockMvc.perform(post("/payments")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.currencyId").value("only EUR currency is valid"))
                .andExpect(jsonPath("$.details").value("must not be blank"));
    }

    @Test
    public void validatePaymentForPaymentTypeTwo() throws Exception {
        PaymentCreateDto dto = new PaymentCreateDto();
        dto.setAmount("100");
        dto.setCreditorIbanId(1L);
        dto.setDebtorIbanId(2L);
        dto.setCurrencyId(2L);
        dto.setPaymentTypeId(2L);

        User user = new User();
        user.setName("user1");
        user.setPassword("password");
        String token = jwtProviderService.generateToken(user);

        mockMvc.perform(post("/payments")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.currencyId").value("only USD currency is valid"));
    }

    @Test
    public void validatePaymentForPaymentTypeThree() throws Exception {
        PaymentCreateDto dto = new PaymentCreateDto();
        dto.setAmount("100");
        dto.setCreditorIbanId(1L);
        dto.setDebtorIbanId(2L);
        dto.setCurrencyId(2L);
        dto.setPaymentTypeId(3L);

        User user = new User();
        user.setName("user1");
        user.setPassword("password");
        String token = jwtProviderService.generateToken(user);

        mockMvc.perform(post("/payments")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.bicCodeId").value("must not be null"));
    }
}
