package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personService.getPersons();
    }
    @GetMapping("/person")
    public List<Person> getPersonByLastName(@RequestParam String name) {
        return personService.getPersonByLastName(name);
    }
}
