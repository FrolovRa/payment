package com.crud.payment.controller;

import com.crud.payment.SpringTest;
import com.crud.payment.service.JwtProviderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class AuthControllerTest extends SpringTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtProviderService jwtProviderService;

    @Test
    public void shouldAuth() throws Exception {
        String result = mockMvc.perform(post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"user1\", \"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertThat(result).isNotEmpty();
        assertThat(jwtProviderService.validateToken(result)).isTrue();
    }

    @Test
    public void shouldNotAuth() throws Exception {
        mockMvc.perform(post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"0\", \"password\":\"0\"}"))
                .andExpect(status().isNotFound());
    }
}
