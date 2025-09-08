package com.openclassrooms.safetynet.dto;


import lombok.Data;

import java.util.List;

@Data
public class CommunityEmailResponse {
    List<String> emailList;

    public CommunityEmailResponse(List<String> emailList) {
        this.emailList = emailList;
    }
}

