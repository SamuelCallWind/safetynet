package com.openclassrooms.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class FloodResponse {
    private List<StationDto> stationDtos;

    public FloodResponse(List<StationDto> stationDtos) {
        this.stationDtos = stationDtos;
    }
}
