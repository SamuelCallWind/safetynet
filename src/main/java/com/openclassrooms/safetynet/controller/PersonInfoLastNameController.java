package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.PersonInfoLastNameReponse;
import com.openclassrooms.safetynet.service.PersonInfoLastNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonInfoLastNameController {

    @Autowired
    PersonInfoLastNameService personInfoLastNameService;

    @GetMapping("/personInfolastName")
    public PersonInfoLastNameReponse getPersonInfolastName(@RequestParam String lastName) {
        return personInfoLastNameService.getPersonInfoLastName(lastName);
    }
}
