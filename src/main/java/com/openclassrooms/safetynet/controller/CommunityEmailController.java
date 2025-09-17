package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.CommunityEmailResponse;
import com.openclassrooms.safetynet.service.CommunityEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CommunityEmailController {

    @Autowired
    CommunityEmailService communityEmailService;

    @GetMapping("/communityEmail")
    public CommunityEmailResponse getCommunityEmail(@RequestParam String city) {
        log.info("GET method called for the city: {}", city);
        return new CommunityEmailResponse(communityEmailService.getEmailsFromCity(city));
    }
}
