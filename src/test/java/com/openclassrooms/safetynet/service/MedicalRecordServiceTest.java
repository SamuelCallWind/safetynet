package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Root;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/**
 * Unit tests for {@link MedicalRecordService}.
 * Static query methods use a real in-memory Root object (no stubbing).
 */
class MedicalRecordServiceTest {

    private RootRepository rootRepository;
    private MedicalRecordService service;

    private Root root;
    private Medicalrecord johnRecord;
    private Medicalrecord janeRecord;

    @BeforeEach
    void setUp() {
        root = new Root();
        root.setMedicalrecords(new ArrayList<>());

        johnRecord = new Medicalrecord(
                "John", "Doe", "01/01/1980",
                List.of("med1", "med2"), List.of("pollen"));
        janeRecord = new Medicalrecord(
                "Jane", "Smith", "02/02/1990",
                List.of("ibuprofen"), List.of());

        root.getMedicalrecords().addAll(List.of(johnRecord, janeRecord));

        rootRepository = mock(RootRepository.class);
        doReturn(root).when(rootRepository).getRoot();

        service = new MedicalRecordService();
        service.rootRepository = rootRepository;
    }


    @Test
    void getMedicalRecordFromLastName_returnsMatchingRecords() {
        List<Medicalrecord> result =
                MedicalRecordService.getMedicalRecordFromLastName("Doe", rootRepository);

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void getMedicationFromFullName_returnsMedications() {
        List<String> meds =
                MedicalRecordService.getMedicationFromFullName("John", "Doe", rootRepository);

        assertEquals(List.of("med1", "med2"), meds);
    }

    @Test
    void getAllergiesFromFullName_returnsAllergies() {
        List<String> allergies =
                MedicalRecordService.getAllergiesFromFullName("John", "Doe", rootRepository);

        assertEquals(List.of("pollen"), allergies);
    }

    @Test
    void getBirthdateFromPersonsName_returnsBirthdate() {
        String birthdate =
                MedicalRecordService.getBirthdateFromPersonsName("Jane", "Smith", rootRepository);

        assertEquals("02/02/1990", birthdate);
    }

    @Test
    void getMedicationFromFullName_returnsEmptyWhenNotFound() {
        List<String> meds =
                MedicalRecordService.getMedicationFromFullName("Foo", "Bar", rootRepository);

        assertTrue(meds.isEmpty());
    }

    @Test
    void addMedicalRecord_delegatesToRepository() {
        Medicalrecord newRec = new Medicalrecord("Alice", "Wonder", "03/03/2000",
                List.of("vitaminC"), List.of("cats"));

        service.addMedicalRecord(newRec);

        verify(rootRepository).addMedicalRecord(newRec);
    }

    @Test
    void modifyMedicalRecord_delegatesToRepository() {
        service.modifyMedicalRecord(johnRecord);
        verify(rootRepository).modifyMedicalRecord(johnRecord);
    }

    @Test
    void removeMedicalRecord_delegatesToRepository() {
        service.removeMedicalRecord("John", "Doe");
        verify(rootRepository).removeMedicalRecord("John", "Doe");
    }
}
