package com.hotel.service;

import com.hotel.dto.OccupancyRequest;
import com.hotel.dto.OccupancyResponse;
import com.hotel.exception.InvalidRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OccupancyServiceTest {

    private OccupancyService occupancyService;

    @BeforeEach
    public void setUp() {
        occupancyService = new OccupancyService();
    }

    @Test
    public void testCalculateOccupancyFirstCase() {
        // Given
        OccupancyRequest request = new OccupancyRequest();
        request.setPotentialGuests(Arrays.asList(374.0, 155.0, 99.99, 45.0, 23.0, 101.0, 209.0, 115.0, 22.0, 100.0));
        request.setPremiumRooms(3);
        request.setEconomyRooms(3);

        // When
        OccupancyResponse response = occupancyService.calculateOccupancy(request);

        // Then
        assertEquals(3, response.getUsagePremium());
        assertEquals(738.0, response.getRevenuePremium());
        assertEquals(3, response.getUsageEconomy());
        assertEquals(167.99, response.getRevenueEconomy());
    }

    @Test
    public void testCalculateOccupancySecondCase() {
        // Given
        OccupancyRequest request = new OccupancyRequest();
        request.setPotentialGuests(Arrays.asList(374.0, 155.0, 101.0, 99.99, 209.0, 115.0, 100.0, 45.0, 23.0, 22.0));
        request.setPremiumRooms(7);
        request.setEconomyRooms(5);

        // When
        OccupancyResponse response = occupancyService.calculateOccupancy(request);

        // Then
        assertEquals(6, response.getUsagePremium());
        assertEquals(1054.0, response.getRevenuePremium());
        assertEquals(4, response.getUsageEconomy());
        assertEquals(189.99, response.getRevenueEconomy());
    }

    @Test
    public void testCalculateOccupancyThirdCase() {
        // Given
        OccupancyRequest request = new OccupancyRequest();
        request.setPotentialGuests(Arrays.asList(374.0, 155.0, 101.0, 99.99, 209.0, 115.0, 100.0, 45.0, 23.0, 22.0));
        request.setPremiumRooms(2);
        request.setEconomyRooms(7);

        // When
        OccupancyResponse response = occupancyService.calculateOccupancy(request);

        // Then
        assertEquals(2, response.getUsagePremium());
        assertEquals(583.0, response.getRevenuePremium());
        assertEquals(4, response.getUsageEconomy());
        assertEquals(189.99, response.getRevenueEconomy());
    }

    @Test
    public void testCalculateOccupancyWithExceptionThrown() {
        // Given
        OccupancyRequest request = new OccupancyRequest();
        request.setPotentialGuests(Collections.emptyList());
        request.setPremiumRooms(3);
        request.setEconomyRooms(3);

        // When & Then
        InvalidRequestException thrown = assertThrows(
                InvalidRequestException.class,
                () -> occupancyService.calculateOccupancy(request)
        );
        assertEquals("Potential guests list must not be empty.", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());
    }
}
