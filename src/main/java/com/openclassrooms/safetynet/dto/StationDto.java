package com.openclassrooms.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class StationDto {
    private String address;
    private List<HouseholdDto> householdDtos;

    public StationDto(String address, List<HouseholdDto> householdDtos) {
        this.address = address;
        this.householdDtos = householdDtos;
    }
}
