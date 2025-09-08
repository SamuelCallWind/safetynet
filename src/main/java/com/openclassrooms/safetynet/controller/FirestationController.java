package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FirestationController {

    @Autowired
    PersonService personService;

    @GetMapping("/firestation")
    public List<Person> getFirestation(@RequestParam int stationNumber) {
        return personService.getPersonsByFirestationNumber(stationNumber);
    }

}
