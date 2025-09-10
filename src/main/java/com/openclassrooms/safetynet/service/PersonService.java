package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    RootRepository rootRepository;

    public List<Person> getPersons() {
        return new ArrayList<>(rootRepository.getRoot().getPersons());
    }

    public List<Person> getPersonByLastName(String lastName) {
        List<Person> result = new ArrayList<>();
        rootRepository.getRoot().getPersons().forEach(person -> {
            if (person.getLastName().equals(lastName)) {
                result.add(person);
            }
        });
        return result;
    }

    public List<Person> getPersonsByFirestationNumber(int firestationNumber) {
        return getPersonsByFirestationNumber(firestationNumber, rootRepository);
    }

    public static List<Person> getPersonsByFirestationNumber(int firestationNumber, RootRepository rootRepository) {
        List<String> addressOfFirestation = new ArrayList<>();
        List<Person> result = new ArrayList<>();

        // First, I'm checking the address of the station number
        rootRepository.getRoot().getFirestations().forEach(firestation -> {
            if (firestation.getStation() == firestationNumber) {
                addressOfFirestation.add(firestation.getAddress());
            }
        });

        rootRepository.getRoot().getPersons().forEach(person -> {
            if (addressOfFirestation.contains(person.getAddress())) {
                result.add(person);
            }
        });
        return result;
    }

    public void addPerson(Person person) {
        rootRepository.addPerson(person);
    }

    public void removePerson(Person person) {
        rootRepository.removePerson(person);
    }

    public void modifyPerson(Person person) {
        rootRepository.modifyPerson(person);
    }

}
