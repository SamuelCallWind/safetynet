package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Root;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PhoneAlertServiceTest {

    private Firestation firestation;
    private Person person;
    private PhoneAlertService phoneAlertService;

    @Mock
    RootRepository rootRepository;
    @Mock
    Root root;

    @BeforeEach
    public void setup() {
        phoneAlertService = new PhoneAlertService(rootRepository);
        firestation = new Firestation("2 dangerous road", 5);
        person = new Person("Bob",
                "Dylan",
                "2 dangerous road",
                "London",
                "555",
                "bobdylantest@email.com",
                50);
    }

    @Test
    public void getPhoneForFirestationTest() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getFirestations()).thenReturn(List.of(firestation));
        when(root.getPersons()).thenReturn(List.of(person));

        String result = phoneAlertService.getPhoneForFirestation(5).get(0);

        assertEquals("555", result);
    }
}
