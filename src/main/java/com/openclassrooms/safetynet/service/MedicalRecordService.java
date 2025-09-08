package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.repository.RootRepository;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordService {

    public static List<Medicalrecord> getMedicalRecordFromLastName( String lastName, RootRepository rootRepository) {
        return rootRepository.getRoot().getMedicalrecords().stream()
                .filter(record -> record.getLastName().equals(lastName))
                .toList();
    }

    public static List<String> getMedicationFromFullName(String firstName, String lastName, RootRepository rootRepository) {
        return rootRepository.getRoot().getMedicalrecords().stream()
                .filter(record -> record.getFirstName().equals(firstName) && record.getLastName().equals(lastName))
                .findFirst().map(Medicalrecord::getMedications)
                .orElse(new ArrayList<>());
    }

    public static List<String> getAllergiesFromFullName(String firstName, String lastName, RootRepository rootRepository) {
        return rootRepository.getRoot().getMedicalrecords().stream()
                .filter(record -> record.getFirstName().equals(firstName) && record.getLastName().equals(lastName))
                .findFirst().map(Medicalrecord::getAllergies)
                .orElse(new ArrayList<>());
    }

    public static String getAgeFromPersonsName(String firstName, String lastName, RootRepository rootRepository) {
        return rootRepository.getRoot().getMedicalrecords().stream()
                .filter(record -> record.getFirstName().equals(firstName) && record.getLastName().equals(lastName))
                .findFirst().map(Medicalrecord::getBirthdate)
                .orElse("");
    }
}
