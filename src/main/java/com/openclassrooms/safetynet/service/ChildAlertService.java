package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.ChildAlertResponse;
import com.openclassrooms.safetynet.dto.ChildDto;
import com.openclassrooms.safetynet.dto.HouseholdMemberDto;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.util.DateUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChildAlertService {

    private RootRepository rootRepository;

    public ChildAlertService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public ChildAlertResponse getChildAlert(String address) {
        List<Person> personsAtAddress = rootRepository.getRoot().getPersons().stream()
                .filter(p -> p.getAddress().equals(address))
                .toList();

        List<ChildDto> children = new ArrayList<>();
        List<HouseholdMemberDto> householdMembers = new ArrayList<>();

        personsAtAddress.forEach(person -> {
            int age = calculateAge(person);
            if (age <= 18) {
                children.add(new ChildDto(person.getFirstName(), person.getLastName(), age));
            } else {
                householdMembers.add(new HouseholdMemberDto(person.getFirstName(), person.getLastName()));
            }
        });
        return new ChildAlertResponse(children, householdMembers);
    }

    private int calculateAge(Person person) {
        String firstName = person.getFirstName();
        String lastName = person.getLastName();

        return rootRepository.getRoot().getMedicalrecords().stream()
                .filter(medicalRecord ->
                        medicalRecord.getFirstName().equals(firstName) && medicalRecord.getLastName().equals(lastName)
                ).findFirst()
                .map(medicalRecord -> {
                    return DateUtils.calculateAge(medicalRecord.getBirthdate());
                }).orElse(-1);
    }

}
