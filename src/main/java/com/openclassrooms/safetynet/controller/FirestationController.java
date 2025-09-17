package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.service.FirestationService;
import com.openclassrooms.safetynet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class FirestationController {

    @Autowired
    PersonService personService;
    @Autowired
    FirestationService firestationService;

    @GetMapping("/firestation")
    public List<Person> getFirestation(@RequestParam int stationNumber) {
        log.info("GET method called for the station number: {}", stationNumber);
        return personService.getPersonsByFirestationNumber(stationNumber);
    }

    @PostMapping("/firestation")
    public void addFirestation(@RequestBody Firestation firestation) {
        log.info("POST method called for the firestation: {}", firestation);
        firestationService.addFirestation(firestation);
    }

    @DeleteMapping("/firestation")
    public void removeFirestation(@RequestBody Firestation firestation) {
        log.info("DELETE method called for the firestation: {}", firestation);
        firestationService.removeFirestation(firestation);
    }

    @PutMapping("/firestation")
    public void modifyFirestation(@RequestBody Firestation firestation) {
        log.info("PUT method called for the firestation: {}", firestation);
        firestationService.modifyFirestation(firestation);
    }
}
