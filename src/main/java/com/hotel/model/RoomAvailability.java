package com.hotel.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoomAvailability {
    private final int premiumRooms;
    private final int economyRooms;
    private int usagePremium = 0;
    private int usageEconomy = 0;

    public boolean hasAvailablePremiumRooms() {
        return usagePremium < premiumRooms;
    }

    public boolean hasAvailableEconomyRooms() {
        return usageEconomy < economyRooms;
    }

    public void allocatePremiumRoom() {
        if (hasAvailablePremiumRooms()) {
            usagePremium++;
        } else {
            throw new IllegalStateException("No available premium rooms");
        }
    }

    public void allocateEconomyRoom() {
        if (hasAvailableEconomyRooms()) {
            usageEconomy++;
        } else {
            throw new IllegalStateException("No available economy rooms");
        }
    }
}
