package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.dto.PersonDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/person")
    public List<Person> getPersonByLastName(@RequestParam String name) {
        return personService.getPersonByLastName(name);
    }

    @PostMapping("/person")
    public void addPerson(@RequestBody PersonDto personDto) {
        personService.addPerson(new Person(personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getAddress(),
                personDto.getCity(),
                personDto.getPhone(),
                personDto.getEmail(),
                personDto.getZip()
        ));
    }
}
