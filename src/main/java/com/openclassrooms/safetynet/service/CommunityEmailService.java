package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityEmailService {

    @Autowired
    RootRepository rootRepository;

    public List<String> getEmailsFromCity(String city) {
        return rootRepository.getRoot().getPersons().stream().filter(person -> person.getCity().equals(city))
                .map(Person::getEmail)
                .toList();
    }
}
