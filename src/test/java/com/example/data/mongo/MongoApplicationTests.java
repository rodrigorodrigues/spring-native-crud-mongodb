package com.example.data.mongo;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MongoApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$..id").hasJsonPath());
    }

    @Test
    public void testFindById() throws Exception {
        Order order = orderRepository.findAll().iterator().next();

        mockMvc.perform(get("/api/orders/" + order.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId()))
                .andExpect(jsonPath("$.customerId").value(order.getCustomerId()))
                .andExpect(jsonPath("$.orderDate").exists());
    }

    @Test
    public void testCreate() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setCustomerId("1234l");
        order.addItem(new LineItem("a2", 1.30, 5));
        order.addItem(new LineItem("z10", 100, 2));

        mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.customerId").value(order.getCustomerId()))
                .andExpect(jsonPath("$.orderDate").exists())
                .andExpect(jsonPath("$.items").isArray());
    }
}