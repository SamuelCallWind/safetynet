package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FirestationPerson;
import com.openclassrooms.safetynet.dto.FirestationResponse;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class PersonService {

    @Autowired
    RootRepository rootRepository;

    PersonService() {}
    PersonService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public List<Person> getPersons() {
        return new ArrayList<>(rootRepository.getRoot().getPersons());
    }

    public List<Person> getPersonByLastName(String lastName) {
        List<Person> result = new ArrayList<>();
        try {
            rootRepository.getRoot().getPersons().forEach(person -> {
                if (person.getLastName().equals(lastName)) {
                    result.add(person);
                }
            });
        } catch (Exception e) {
            log.error("Error while getting the person by last name: {}",lastName, e);
        }
        return result;
    }

    public List<Person> getPersonsByFirestationNumber(int firestationNumber) {
        return getPersonsByFirestationNumber(firestationNumber, rootRepository);
    }

    public static List<Person> getPersonsByFirestationNumber(int firestationNumber, RootRepository rootRepository) {
        List<String> addressOfFirestation = new ArrayList<>();
        List<Person> result = new ArrayList<>();
        try {
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
        } catch (RuntimeException e) {
            log.error("Error while getting the Firestation by the number: {}", firestationNumber, e);
        }
        return result;
    }

    public FirestationResponse getFirestationResponse(int firestationNumber) {
        List<String> addressOfFirestation = new ArrayList<>();
        List<FirestationPerson> result = new ArrayList<>();
        int numberOfAdult = 0;
        int numberOfChildren = 0;

        try {
            rootRepository.getRoot().getFirestations().forEach(firestation -> {
                if (firestation.getStation() == firestationNumber) {
                    addressOfFirestation.add(firestation.getAddress());
                }
            });

            rootRepository.getRoot().getPersons().forEach(person -> {
                if (addressOfFirestation.contains(person.getAddress())) {
                    result.add(new FirestationPerson(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone()));
                }
            });

            // Logic to check if the person is a child or an adult for the response
            for (Medicalrecord medicalrecord : rootRepository.getRoot().getMedicalrecords()) {
                for (FirestationPerson person : result) {
                    if (medicalrecord.getFirstName().equals(person.getFirstName()) && medicalrecord.getLastName().equals(person.getLastName())) {
                        if (DateUtils.calculateAge(medicalrecord.getBirthdate()) > 18) {
                            numberOfAdult++;
                        } else {
                            numberOfChildren++;
                        }
                    }
                }
            }

        } catch (RuntimeException e) {
            log.error("Error while getting the Firestation by the number: {}", firestationNumber, e);
        }
        return new FirestationResponse(result, numberOfAdult, numberOfChildren);
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
