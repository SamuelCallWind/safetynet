package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    RootRepository rootRepository;

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

    public static String getBirthdateFromPersonsName(String firstName, String lastName, RootRepository rootRepository) {
        return rootRepository.getRoot().getMedicalrecords().stream()
                .filter(record -> record.getFirstName().equals(firstName) && record.getLastName().equals(lastName))
                .findFirst().map(Medicalrecord::getBirthdate)
                .orElse("");
    }

    public void addMedicalRecord(Medicalrecord medicalrecord) {
        rootRepository.addMedicalRecord(medicalrecord);
    }

    public void modifyMedicalRecord(String firstName, String lastName, Medicalrecord medicalrecord) {
        rootRepository.modifyMedicalRecord(firstName, lastName, medicalrecord);
    }

    public void removeMedicalRecord(String firstName, String lastName) {
        rootRepository.removeMedicalRecord(firstName, lastName);
    }
}
