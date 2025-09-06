package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FireResponse;
import com.openclassrooms.safetynet.service.FireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireController {

    @Autowired
    FireService fireService;

    @GetMapping("/fire")
    public FireResponse getFire(@RequestParam String address) {
        return fireService.getPersonsLivingAtAddress(address);
    }
}
