package com.hotel.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

import com.hotel.dto.OccupancyRequest;
import com.hotel.dto.OccupancyResponse;
import com.hotel.service.OccupancyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

@WebMvcTest(OccupancyController.class)
public class OccupancyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OccupancyService occupancyService;

    @Autowired
    private ObjectMapper objectMapper;

    private OccupancyRequest occupancyRequest;
    private OccupancyResponse occupancyResponse;
    private List<Double> mockData;

    @BeforeEach
    public void setUp() throws IOException {
        // Read mock data from JSON file
        String jsonFilePath = "src/test/resources/mockData.json";
        mockData = objectMapper.readValue(Files.readAllBytes(Paths.get(jsonFilePath)), new TypeReference<List<Double>>() {});

        // Create the request and response objects
        occupancyRequest = new OccupancyRequest();
        occupancyRequest.setPotentialGuests(mockData);

        // Example response setup based on mockData
        occupancyResponse = new OccupancyResponse();
        occupancyResponse.setUsagePremium(5); // Example value
        occupancyResponse.setRevenuePremium(mockData.stream().limit(5).mapToDouble(Double::doubleValue).sum());
        occupancyResponse.setUsageEconomy(5); // Example value
        occupancyResponse.setRevenueEconomy(mockData.stream().skip(5).mapToDouble(Double::doubleValue).sum());
    }

    @Test
    public void testCalculateOccupancy() throws Exception {
        given(occupancyService.calculateOccupancy(any(OccupancyRequest.class)))
                .willReturn(occupancyResponse);

        mockMvc.perform(post("/occupancy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(occupancyRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usagePremium").value(occupancyResponse.getUsagePremium()))
                .andExpect(jsonPath("$.revenuePremium").value(occupancyResponse.getRevenuePremium()))
                .andExpect(jsonPath("$.usageEconomy").value(occupancyResponse.getUsageEconomy()))
                .andExpect(jsonPath("$.revenueEconomy").value(occupancyResponse.getRevenueEconomy()));
    }
}
