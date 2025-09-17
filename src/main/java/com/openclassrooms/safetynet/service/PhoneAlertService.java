package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PhoneAlertService {

    @Autowired
    RootRepository rootRepository;

    PhoneAlertService() {}
    PhoneAlertService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }


    public List<String> getPhoneForFirestation(int firestationNumber) {
        try {
            List<String> firestationAddresses = rootRepository.getRoot().getFirestations().stream()
                    .filter(f -> (f.getStation() == firestationNumber))
                    .map(Firestation::getAddress)
                    .collect(Collectors.toList());
            List<String> phoneNumbers = rootRepository.getRoot().getPersons().stream()
                    .filter(person -> firestationAddresses.contains(person.getAddress()))
                    .map(Person::getPhone)
                    .toList();

            return phoneNumbers;
        } catch (RuntimeException e) {
            log.error("Failed to get the Phone numbers for firestation number: {}", firestationNumber, e);
        }
        return new ArrayList<>();
    }
}
