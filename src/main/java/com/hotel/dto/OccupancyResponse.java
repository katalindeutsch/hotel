package com.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OccupancyResponse {
    private int usagePremium;
    private double revenuePremium;
    private int usageEconomy;
    private double revenueEconomy;
}
