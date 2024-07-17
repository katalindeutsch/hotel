package com.hotel.controller;

import com.hotel.dto.OccupancyRequest;
import com.hotel.dto.OccupancyResponse;
import com.hotel.service.OccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/occupancy")
public class OccupancyController {

    @Autowired
    private OccupancyService occupancyService;

    @PostMapping
    public OccupancyResponse calculateOccupancy(@RequestBody OccupancyRequest request) {
        return occupancyService.calculateOccupancy(request);
    }
}
