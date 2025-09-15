package com.openclassrooms.safetynet.service;


import com.openclassrooms.safetynet.dto.PersonInfoLastNameReponse;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Root;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.util.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonInfoLastNameServiceTest {

    @Mock
    Root root;

    @Mock
    private RootRepository rootRepository;

    private PersonInfoLastNameService personInfoLastNameService;
    private Person john;
    private Medicalrecord medicalrecord;


    @BeforeEach
    public void setup() {
        personInfoLastNameService = new PersonInfoLastNameService(rootRepository);
        john = new Person("John",
                "Lennon",
                "221 Baker st",
                "London",
                "555-555-555",
                "johnlenon@email.com",
                55555);
        medicalrecord = new Medicalrecord("John", "Lennon", "01/01/1995", new ArrayList<>(), new ArrayList<>());

    }

    @Test
    public void getPersonInfoLastName() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getPersons()).thenReturn(List.of(john));
        when(root.getMedicalrecords()).thenReturn(List.of(medicalrecord));
        try (MockedStatic<MedicalRecordService> medicalRecordServiceMockedStatic = mockStatic(MedicalRecordService.class);
            MockedStatic<DateUtils> dateUtils = mockStatic(DateUtils.class);) {

            medicalRecordServiceMockedStatic.when(() -> {
               MedicalRecordService.getMedicationFromFullName("John", "Lennon", rootRepository);
            }).thenReturn(List.of("testMedication"));

            medicalRecordServiceMockedStatic.when(() -> {
                MedicalRecordService.getAllergiesFromFullName("John", "Lennon", rootRepository);
            }).thenReturn(List.of(""));

            medicalRecordServiceMockedStatic.when(() -> {
                MedicalRecordService.getBirthdateFromPersonsName("John", "Lennon", rootRepository);
            }).thenReturn("01/01/1995");

            dateUtils.when(() -> {
                DateUtils.calculateAge("01/01/1995");
            }).thenReturn(30);
        }
        PersonInfoLastNameReponse result = personInfoLastNameService.getPersonInfoLastName("Lennon");

        assertEquals("John", result.getPersons().get(0).getFirstName());
        assertEquals("Lennon", result.getPersons().get(0).getLastName());
    }
}
