package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FloodResponse;
import com.openclassrooms.safetynet.service.FloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FloodController {

    @Autowired
    FloodService floodService;

    @GetMapping("/flood/stations")
    public FloodResponse getFloodResponse(@RequestParam List<Integer> stations) {
        return floodService.getFloodResponse(stations);
    }
}
