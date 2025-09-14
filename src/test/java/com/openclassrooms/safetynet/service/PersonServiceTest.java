package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Root;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private RootRepository rootRepository;

    @Mock
    private Root root;

    @InjectMocks
    private PersonService personService;

    private Person jean;
    private Person alice;
    private Firestation station1;

    @BeforeEach
    void setUp() {
        jean = new Person("Jean", "Bodin",
                "123 Paris St", "Paris",
                "555-111-222", "jean@email.com", 75001);
        alice = new Person("Alice", "Smith",
                "456 London Rd", "London",
                "555-333-444", "alice@email.com", 12345);
        station1 = new Firestation("123 Paris St", 1);

    }

    @Test
    void getPersons_returnsAllPersons() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getPersons()).thenReturn(List.of(jean, alice));

        List<Person> result = personService.getPersons();

        assertEquals(2, result.size());
        assertTrue(result.contains(jean));
        assertTrue(result.contains(alice));
    }

    @Test
    void getPersonByLastName_filtersByLastName() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getPersons()).thenReturn(List.of(jean, alice));

        List<Person> result = personService.getPersonByLastName("Bodin");

        assertEquals(1, result.size());
        assertEquals("Bodin", result.get(0).getLastName());
    }

    @Test
    void getPersonByLastName_returnsEmptyWhenNoMatch() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getPersons()).thenReturn(List.of(jean));

        List<Person> result = personService.getPersonByLastName("Unknown");

        assertTrue(result.isEmpty());
    }

    @Test
    void getPersonsByFirestationNumber_returnsMatchingPersons() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getFirestations()).thenReturn(List.of(station1));
        when(root.getPersons()).thenReturn(List.of(jean, alice));

        List<Person> result = personService.getPersonsByFirestationNumber(1);

        assertEquals(1, result.size());
        assertEquals("Jean", result.get(0).getFirstName());
    }

    @Test
    void getPersonsByFirestationNumber_returnsEmptyWhenNoStationMatch() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getFirestations()).thenReturn(List.of(station1));
        when(root.getPersons()).thenReturn(List.of(jean));

        List<Person> result = personService.getPersonsByFirestationNumber(99);

        assertTrue(result.isEmpty());
    }

    @Test
    void addPerson_delegatesToRepository() {
        personService.addPerson(jean);
        verify(rootRepository).addPerson(jean);
    }

    @Test
    void removePerson_delegatesToRepository() {
        personService.removePerson(jean);
        verify(rootRepository).removePerson(jean);
    }

    @Test
    void modifyPerson_delegatesToRepository() {
        personService.modifyPerson(alice);
        verify(rootRepository).modifyPerson(alice);
    }

    @Test
    void getPersons_handlesEmptyListSafely() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getPersons()).thenReturn(Collections.emptyList());
        List<Person> result = personService.getPersons();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
