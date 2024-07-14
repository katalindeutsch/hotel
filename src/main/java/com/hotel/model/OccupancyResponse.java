package com.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OccupancyResponse {
    private int usagePremium;
    private double revenuePremium;
    private int usageEconomy;
    private double revenueEconomy;
}
