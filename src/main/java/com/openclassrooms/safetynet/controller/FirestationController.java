package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.service.FirestationService;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FirestationController {

    @Autowired
    PersonService personService;
    @Autowired
    FirestationService firestationService;

    @GetMapping("/firestation")
    public List<Person> getFirestation(@RequestParam int stationNumber) {
        return personService.getPersonsByFirestationNumber(stationNumber);
    }

    @PostMapping("/firestation")
    public void addFirestation(@RequestBody Firestation firestation) {
        firestationService.addFirestation(firestation);
    }

    @DeleteMapping("/firestation")
    public void removeFirestation(@RequestBody Firestation firestation) {
        firestationService.removeFirestation(firestation);
    }
}
