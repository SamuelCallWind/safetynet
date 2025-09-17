package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FireResponse;
import com.openclassrooms.safetynet.service.FireService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FireController {

    @Autowired
    FireService fireService;

    @GetMapping("/fire")
    public FireResponse getFire(@RequestParam String address) {
        log.info("GET method called for the address: {}", address);
        return fireService.getPersonsLivingAtAddress(address);
    }
}
