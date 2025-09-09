package com.openclassrooms.safetynet.dto;

import lombok.Data;
import java.util.List;

@Data
public class HouseholdDto {
    List<FloodPersonDto> floodPersonDtos;

    public HouseholdDto(List<FloodPersonDto> floodPersonDtos) {
        this.floodPersonDtos = floodPersonDtos;
    }
}
