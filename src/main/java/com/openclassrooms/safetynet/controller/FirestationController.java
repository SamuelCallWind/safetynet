package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FirestationController {
    /*
    @Autowired
    RootRepository rootRepository;

    @GetMapping("/firestation")
    public List<Firestation> getFirestation(@RequestParam int stationNumber) {
        return rootRepository.getFirestationByStationNumber(stationNumber);
    }
    
     */

}
