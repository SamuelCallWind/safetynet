package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.dto.PersonDto;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/person")
    public List<Person> getPersonByLastName(@RequestParam String lastName) {
        log.info("GET method called with lastName={}", lastName);
        return personService.getPersonByLastName(lastName);
    }

    @PostMapping("/person")
    public void addPerson(@RequestBody PersonDto personDto) {
        log.info("POST method called with lastName={}", personDto.getLastName());
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
        log.info("DELETE method called with lastName={}", personDto.getLastName());
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
        log.info("PUT method called with lastName={}", person.getLastName());
        personService.modifyPerson(person);
    }
}
