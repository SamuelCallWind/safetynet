package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PhoneAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneAlertController {

    @Autowired
    PhoneAlertService phoneAlertService;

    @GetMapping("/phoneAlert")
    public List<String> getPhoneAlert(@RequestParam int firestationNumber) {
        return phoneAlertService.getPhoneForFirestation(firestationNumber);
    }
}
