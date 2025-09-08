package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.CommunityEmailResponse;
import com.openclassrooms.safetynet.service.CommunityEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommunityEmailController {

    @Autowired
    CommunityEmailService communityEmailService;

    @GetMapping("/communityEmail")
    public CommunityEmailResponse getCommunityEmail(@RequestParam String city) {
        return new CommunityEmailResponse(communityEmailService.getEmailsFromCity(city));
    }
}
