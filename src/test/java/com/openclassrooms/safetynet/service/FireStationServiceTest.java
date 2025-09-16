package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Root;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FireStationServiceTest {

    private FirestationService firestationService;
    private Firestation firestation;

    @Mock
    RootRepository rootRepository;
    @Mock
    Root root;

    @BeforeEach
    public void setup() {
        firestationService = new FirestationService(rootRepository);
        firestation = new Firestation("123 Baker st", 9);
    }

    @Test
    public void getFirestationAddressFromNumber() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getFirestations()).thenReturn(new ArrayList<>(List.of(new Firestation("123 Baker st", 9))));

        String result = FirestationService.getFirestationAddressFromNumber(9, rootRepository);

        assertEquals("123 Baker st", result);
    }

    @Test
    public void addFirestationTest_shouldThrowRuntimeError() {
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getFirestations()).thenReturn(List.of(firestation));

        assertThrows(RuntimeException.class, () -> firestationService.addFirestation(new Firestation("123 Baker st", 9)));
    }

    @Test
    public void addFirestationTest_shouldNotThrowError() {
        Firestation mockOfDatabase = new Firestation("does not exist 55", 15);
        when(rootRepository.getRoot()).thenReturn(root);
        when(root.getFirestations()).thenReturn(List.of(mockOfDatabase));
        doNothing().when(rootRepository).addFirestation(firestation);

        firestationService.addFirestation(firestation);

        verify(rootRepository).addFirestation(firestation);
    }

    @Test
    public void removeFirestation_shouldNotThrowError() {
        doNothing().when(rootRepository).removeFirestation(firestation);
        firestationService.removeFirestation(firestation);
        verify(rootRepository).removeFirestation(firestation);
    }

    @Test
    public void modifyFirestation_shouldNotThrowError() {
        doNothing().when(rootRepository).modifyFirestation(firestation);
        firestationService.modifyFirestation(firestation);
        verify(rootRepository).modifyFirestation(firestation);
    }


}
