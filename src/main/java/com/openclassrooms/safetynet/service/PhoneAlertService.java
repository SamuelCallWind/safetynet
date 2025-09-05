package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneAlertService {

    @Autowired
    RootRepository rootRepository;


    public List<String> getPhoneForFirestation(int firestationNumber) {
        //Retrieve the firestation addresses
        List<String> firestationAddresses = rootRepository.getRoot().getFirestations().stream()
                .filter(f -> (f.getStation() == firestationNumber))
                        .map(Firestation::getAddress)
                                .collect(Collectors.toList());
        List<String> phoneNumbers = rootRepository.getRoot().getPersons().stream()
                .filter(person -> firestationAddresses.contains(person.getAddress()))
                .map(Person::getPhone)
                .toList();

        return phoneNumbers;
    }
}
