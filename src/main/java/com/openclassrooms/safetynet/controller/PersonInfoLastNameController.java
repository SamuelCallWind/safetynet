package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.PersonInfoLastNameReponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonInfoLastNameController {

    @GetMapping("/personInfolastName")
    public PersonInfoLastNameReponse getPersonInfolastName() {
        return null;
    }
}
