package com.hotel.service;

import com.hotel.dto.OccupancyRequest;
import com.hotel.dto.OccupancyResponse;
import com.hotel.exception.InvalidRequestException;
import com.hotel.model.Revenue;
import com.hotel.model.RoomAvailability;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OccupancyService {

    private static final String EMPTY_GUEST_LIST = "Potential guests list must not be empty.";

    public OccupancyResponse calculateOccupancy(OccupancyRequest request) {
        validateRequest(request);

        List<Double> guests = request.getPotentialGuests();
        guests.sort(Collections.reverseOrder());

        RoomAvailability roomAvailability = new RoomAvailability(request.getPremiumRooms(), request.getEconomyRooms());
        Revenue revenue = new Revenue();

        for (double guestWillingness : guests) {
            if (isPremiumGuest(guestWillingness) && roomAvailability.hasAvailablePremiumRooms()) {
                roomAvailability.allocatePremiumRoom();
                revenue.addPremiumRevenue(guestWillingness);
            } else if (!isPremiumGuest(guestWillingness) && roomAvailability.hasAvailableEconomyRooms()) {
                roomAvailability.allocateEconomyRoom();
                revenue.addEconomyRevenue(guestWillingness);
            }
        }

        return OccupancyResponse.builder()
                .usagePremium(roomAvailability.getUsagePremium())
                .revenuePremium(revenue.getRevenuePremium())
                .usageEconomy(roomAvailability.getUsageEconomy())
                .revenueEconomy(revenue.getRevenueEconomy())
                .build();
    }

    private void validateRequest(OccupancyRequest request) {
        List<Double> potentialGuests = request.getPotentialGuests();
        if (potentialGuests == null || potentialGuests.isEmpty()) {
            throw new InvalidRequestException(EMPTY_GUEST_LIST, HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isPremiumGuest(double willingnessToPay) {
        return willingnessToPay >= 100;
    }
}