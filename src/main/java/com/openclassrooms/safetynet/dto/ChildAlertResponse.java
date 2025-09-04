package com.openclassrooms.safetynet.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChildAlertResponse {
    private List<ChildDto> children;
    private List<HouseholdMemberDto> householdMembers;

    public ChildAlertResponse(List<ChildDto> children, List<HouseholdMemberDto> householdMembers) {
        this.children = children;
        this.householdMembers = householdMembers;
    }
}
