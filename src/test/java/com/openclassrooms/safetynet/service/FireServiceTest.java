package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Root;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FireServiceTest {

    private Person jean;
    private Firestation firestation;
    private Medicalrecord medicalrecord;

    @Mock
    RootRepository rootRepository;
    @Mock
    Root root;

    FireService fireService;

    @BeforeEach
    public void setup() {
        jean = new Person("Clive",
                "Ferguson",
                "748 Townings Dr",
                "Culver",
                "841-874-6741",
                "clivfd@ymail.com",
                97451);
        firestation = new Firestation("748 Townings Dr", 1);
        medicalrecord = new Medicalrecord("Clive", "Ferguson", "08/08/1990", new ArrayList<>(), new ArrayList<>());
        fireService = new FireService(rootRepository);
    }

    @Test
    public void getPersonLivingAtAddress() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getPersons()).thenReturn(List.of(jean));
        when(root.getFirestations()).thenReturn(List.of(firestation));
        when(root.getMedicalrecords()).thenReturn(List.of(medicalrecord));

        assertEquals("Clive", fireService.getPersonsLivingAtAddress("748 Townings Dr").getPersons().get(0).getFirstName());
    }
}
