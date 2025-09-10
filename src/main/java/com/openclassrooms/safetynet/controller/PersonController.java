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
    public List<Person> getPersonByLastName(@RequestParam String lastName) {
        return personService.getPersonByLastName(lastName);
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

    @DeleteMapping("/person")
    public void deletePerson(@RequestBody PersonDto personDto) {
        personService.removePerson(new Person(personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getAddress(),
                personDto.getCity(),
                personDto.getPhone(),
                personDto.getEmail(),
                personDto.getZip()
        ));
    }

    @PutMapping("/person")
    public void modifyPerson(@RequestBody Person person) {
        personService.modifyPerson(person);
    }
}
