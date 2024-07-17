package com.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Revenue {
    private double revenuePremium = 0;
    private double revenueEconomy = 0;

    public void addPremiumRevenue(double amount) {
        this.revenuePremium += amount;
    }

    public void addEconomyRevenue(double amount) {
        this.revenueEconomy += amount;
    }
}
