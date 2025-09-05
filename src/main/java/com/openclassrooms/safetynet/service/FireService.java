package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FirePersonDto;
import com.openclassrooms.safetynet.dto.FireResponse;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FireService {

    @Autowired
    RootRepository rootRepository;


    public FireResponse getPersonsLivingAt(String address) {
        List<Person> personList = rootRepository.getRoot().getPersons().stream()
                .filter(person -> person.getAddress().equals(address))
                .toList();
        Firestation assignedFirestation = rootRepository.getRoot().getFirestations().stream()
                .filter(f -> f.getAddress().equals(address))
                .findAny().orElse(null);
        List<Medicalrecord> medicalrecordList = rootRepository.getRoot().getMedicalrecords();

        List<FirePersonDto> firePersonDtos = new ArrayList<>();

        for (Person person : personList) {
            for (Medicalrecord mr : medicalrecordList) {
                if (mr.getLastName().equals(person.getLastName()) && mr.getFirstName().equals(person.getFirstName())) {
                    firePersonDtos.add(new FirePersonDto(person.getFirstName(),
                            person.getLastName(),
                            person.getPhone(),
                            DateUtils.calculateAge(mr.getBirthdate()),
                            mr.getMedications(),
                            mr.getAllergies(),
                            FirestationService.getFirestationNumberFromAddress(address, rootRepository)
                            ));
                }
            }
        }
        return new FireResponse(firePersonDtos);
    }
}
