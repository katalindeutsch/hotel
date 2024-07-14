package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OccupancyRequest {
    private int premiumRooms;
    private int economyRooms;
    private List<Double> potentialGuests;
}
