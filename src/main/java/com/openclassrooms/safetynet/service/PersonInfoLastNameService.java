package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.PersonInfoLastNameDto;
import com.openclassrooms.safetynet.dto.PersonInfoLastNameReponse;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonInfoLastNameService {

    @Autowired
    RootRepository rootRepository;

    public PersonInfoLastNameService() {}
    public PersonInfoLastNameService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public PersonInfoLastNameReponse getPersonInfoLastName(String lastName) {
        List<PersonInfoLastNameDto> result = new ArrayList<>();
        rootRepository.getRoot().getPersons().stream().filter(person -> person.getLastName().equals(lastName))
                .forEach(person -> {
                    result.add(new PersonInfoLastNameDto(person.getFirstName(),
                            person.getLastName(),
                            person.getAddress(),
                            DateUtils.calculateAge(MedicalRecordService.getBirthdateFromPersonsName(person.getFirstName(),
                                    person.getLastName(), rootRepository)),
                            person.getEmail(),
                            MedicalRecordService.getMedicationFromFullName(person.getFirstName(), person.getLastName(), rootRepository),
                            MedicalRecordService.getAllergiesFromFullName(person.getFirstName(), person.getLastName(), rootRepository)
                    ));
                });
        return new PersonInfoLastNameReponse(result);
    }


}
