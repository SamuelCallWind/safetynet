package com.openclassrooms.safetynet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Root;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RootRepositoryTest {

    RootRepository rootRepository;
    private Person newPerson;
    private Firestation firestation;

    @Mock
    ObjectMapper mockMapper;

    @BeforeEach
    public void resetRootRepository() {
        rootRepository = new RootRepository();
        // rootRepository.reload();
        newPerson = new Person("John",
                "Watson",
                "221 Baker st",
                "London",
                "555-555-555",
                "johnwatsontest@email.com",
                5000);
    }

    @Test
    public void testSaving_newPersonDataMustBeSavedAndReturnThenDeleted() {
        rootRepository.setFullPath("C:\\Users\\Samuel\\Documents\\JAVA\\safetynet\\src\\main\\java\\com\\openclassrooms\\safetynet\\data\\dataTest.json");
        rootRepository.reload();
        rootRepository.addPerson(newPerson);

        assertEquals(newPerson, rootRepository.getPersonByName("Watson").get(0));

        rootRepository.removePerson(newPerson);
        assertTrue(rootRepository.getPersonByName("Watson").isEmpty());
    }

    @Test
    void testReloadFallsBackToClasspath() throws Exception {
        rootRepository.setMapper(mockMapper);

        when(mockMapper.readValue(any(File.class), eq(Root.class)))
                .thenThrow(new IOException("Error while reading the value"));

        Root fakeRoot = new Root();
        when(mockMapper.readValue(any(InputStream.class), eq(Root.class)))
                .thenReturn(fakeRoot);

        rootRepository.reload();

        assertEquals(fakeRoot, rootRepository.getRoot());
    }

    @Test
    public void testModifyingPerson_shouldNotThrowError() {
        rootRepository.setFullPath("C:\\Users\\Samuel\\Documents\\JAVA\\safetynet\\src\\main\\java\\com\\openclassrooms\\safetynet\\data\\dataTest.json");
        rootRepository.reload();
        rootRepository.addPerson(newPerson);
        Person newJohn = new Person("John",
                "Watson",
                "555 baker st",
                "Dunquerque",
                "111-111-111",
                "notjohnwatsonanymore@email.com",
                00000);

        rootRepository.modifyPerson(newJohn);
        assertEquals("111-111-111", rootRepository.getPersonByName("Watson").get(0).getPhone());

        rootRepository.removePerson(newJohn);
    }

    @Test
    public void addFirestationTest() {
        rootRepository.setFullPath("C:\\Users\\Samuel\\Documents\\JAVA\\safetynet\\src\\main\\java\\com\\openclassrooms\\safetynet\\data\\dataTest.json");
        rootRepository.reload();
        firestation = new Firestation("Untrue address 25", 10);

        rootRepository.addFirestation(firestation);
        assertEquals("Untrue address 25", rootRepository.getRoot().getFirestations()
                .get(rootRepository.getRoot().getFirestations().size()-1).getAddress());

        rootRepository.removeFirestation(firestation);
    }

    @Test
    public void modifyFirestationTest() {
        rootRepository.setFullPath("C:\\Users\\Samuel\\Documents\\JAVA\\safetynet\\src\\main\\java\\com\\openclassrooms\\safetynet\\data\\dataTest.json");
        rootRepository.reload();
        firestation = new Firestation("Untrue address 25", 10);
        Firestation firestationReplacement = new Firestation("Untrue address 25", 200);

        rootRepository.addFirestation(firestation);
        rootRepository.modifyFirestation(firestationReplacement);

        assertEquals(200, rootRepository.getRoot().getFirestations()
                .get(rootRepository.getRoot().getFirestations().size()-1).getStation());

        rootRepository.removeFirestation(firestation);
    }



}
