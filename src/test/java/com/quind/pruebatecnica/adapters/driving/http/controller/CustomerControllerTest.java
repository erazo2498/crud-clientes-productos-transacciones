package com.quind.pruebatecnica.adapters.driving.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quind.pruebatecnica.adapters.driven.jpa.mysql.repositories.ICustomerRepository;
import com.quind.pruebatecnica.adapters.driving.http.dto.request.customer.CustomerRequestDto;
import com.quind.pruebatecnica.configuration.Constants;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
class CustomerControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private ICustomerRepository customerRepository;

    @BeforeEach
     void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .defaultRequest(MockMvcRequestBuilders.get("/").characterEncoding("UTF-8")).build();
    }

    @Test
    void createCustomer() throws Exception {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto(
                "12345",
                "1",
                "Brayan",
                "Erazo",
                "erazo@gmail.com",
                LocalDate.of(1998, Month.MARCH, 24)
        );

        Map<String, String> expectedMapResponse = Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.CUSTOMER_CREATED_MESSAGE);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequestDto))
                ).andExpect(status().isCreated())
                .andExpect(result -> {
                    Map<String, String> map = objectMapper.readValue(result.getResponse().getContentAsString(), Map.class);
                    Assertions.assertNotNull(map);
                    Assertions.assertEquals(expectedMapResponse.get(Constants.RESPONSE_MESSAGE_KEY), map.get(Constants.RESPONSE_MESSAGE_KEY));
                });
    }

    @Test
    void createCustomerWithInvalidIdentificationNumber() throws Exception {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto(
                Strings.EMPTY,
                "1",
                "Brayan",
                "Erazo",
                "erazo@gmail.com",
                LocalDate.of(1998, Month.MARCH, 24)
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequestDto))
                ).andExpect(status().isBadRequest())
                .andExpect(result -> {
                    List<String> listError = objectMapper.readValue(result.getResponse().getContentAsByteArray(), List.class);
                    Assertions.assertNotNull(listError);
                    Assertions.assertEquals("identificationNumber: El numero de identificación no puede ser nulo o vacio", listError.get(0));
                });
    }

    @Test
    void createCustomerWithInvalidIdentificationType() throws Exception {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto(
                "12345",
                null,
                "Brayan",
                "Erazo",
                "erazo@gmail.com",
                LocalDate.of(1998, Month.MARCH, 24)
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequestDto))
                ).andExpect(status().isBadRequest())
                .andExpect(result -> {
                    List<String> listError = objectMapper.readValue(result.getResponse().getContentAsByteArray(), List.class);
                    Assertions.assertNotNull(listError);
                    Assertions.assertEquals("identificationType: El tipo de identificación no puede ser nulo o vacio", listError.get(0));
                });
    }

    @Test
    void createCustomerWithInvalidName() throws Exception {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto(
                "12345",
                "1",
                null,
                "Erazo",
                "erazo@gmail.com",
                LocalDate.of(1998, Month.MARCH, 24)
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequestDto))
                ).andExpect(status().isBadRequest())
                .andExpect(result -> {
                    List<String> listError = objectMapper.readValue(result.getResponse().getContentAsByteArray(), List.class);
                    Assertions.assertNotNull(listError);
                    Assertions.assertEquals("name: El nombre no puede ser nulo o vacio", listError.get(0));
                });
    }

    @Test
    void createCustomerWithInvalidSizeName() throws Exception {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto(
                "12345",
                "1",
                "B",
                "Erazo",
                "erazo@gmail.com",
                LocalDate.of(1998, Month.MARCH, 24)
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequestDto))
                ).andExpect(status().isBadRequest())
                .andExpect(result -> {
                    List<String> listError = objectMapper.readValue(result.getResponse().getContentAsByteArray(), List.class);
                    Assertions.assertNotNull(listError);
                    Assertions.assertEquals("name: El nombre NO puede ser menor a 2 caracteres.", listError.get(0));
                });
    }
}