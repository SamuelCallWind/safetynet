package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.ChildAlertResponse;
import com.openclassrooms.safetynet.dto.ChildDto;
import com.openclassrooms.safetynet.dto.HouseholdMemberDto;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Root;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChildAlertServiceTest {

    private RootRepository rootRepository;
    private ChildAlertService service;

    private Root root;
    private Person childPerson;
    private Person adultPerson;
    private Medicalrecord childRecord;
    private Medicalrecord adultRecord;

    @BeforeEach
    void setUp() {
        root = new Root();
        root.setPersons(new ArrayList<>());
        root.setMedicalrecords(new ArrayList<>());

        // Child: age 10
        childPerson = new Person("Tom", "Kid", "123 Street",
                "City", "555-555-555", "anythingemail@email.com", 25555);
        childRecord = new Medicalrecord("Tom", "Kid", "01/01/2015",
                List.of(), List.of());

        // Adult: age ~35
        adultPerson = new Person("John", "Adult", "123 Street",
                "City", "555-555-555", "anythingemail@email.com", 25555);
        adultRecord = new Medicalrecord("John", "Adult", "01/01/1990",
                List.of(), List.of());

        root.getPersons().addAll(List.of(childPerson, adultPerson));
        root.getMedicalrecords().addAll(List.of(childRecord, adultRecord));

        rootRepository = mock(RootRepository.class);
        when(rootRepository.getRoot()).thenReturn(root);

        service = new ChildAlertService(rootRepository);
    }

    @Test
    void getChildAlert_returnsChildAndHouseholdMembers() {
        ChildAlertResponse response = service.getChildAlert("123 Street");

        assertEquals(1, response.getChildren().size());
        ChildDto child = response.getChildren().get(0);
        assertEquals("Tom", child.getFirstName());
        assertEquals("Kid", child.getLastName());
        assertTrue(child.getAge() <= 18);

        assertEquals(1, response.getHouseholdMembers().size());
        HouseholdMemberDto adult = response.getHouseholdMembers().get(0);
        assertEquals("John", adult.getFirstName());
        assertEquals("Adult", adult.getLastName());
    }

    @Test
    void getChildAlert_returnsEmptyWhenNoChildren() {
        // Remove child
        root.getPersons().remove(childPerson);
        root.getMedicalrecords().remove(childRecord);

        ChildAlertResponse response = service.getChildAlert("123 Street");

        assertTrue(response.getChildren().isEmpty());
        assertEquals(1, response.getHouseholdMembers().size());
    }

    /*
    @Test
    void getChildAlert_skipsPersonWithoutMedicalRecord() {
        // Add person without record
        Person orphan = new Person("Orphan", "NoRecord", "123 Street",
                "City", "555-555-555", "anythingemail@email.com", 25555);
        root.getPersons().add(orphan);

        ChildAlertResponse response = service.getChildAlert("123 Street");

        // Only Tom (child) and John (adult) are considered
        assertEquals(1, response.getChildren().size());
        assertEquals(1, response.getHouseholdMembers().size());
    }

     */
}
