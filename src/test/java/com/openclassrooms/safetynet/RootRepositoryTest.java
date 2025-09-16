package com.openclassrooms.safetynet;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RootRepositoryTest {

    RootRepository rootRepository;
    private Person newPerson;

    @BeforeEach
    public void resetRootRepository() {
        rootRepository = new RootRepository();
        rootRepository.reload();
        newPerson = new Person("John",
                "Watson",
                "221 Baker st",
                "London",
                "555-555-555",
                "johnwatsontest@email.com",
                5000);

    }

    @Test
    public void testSaving_newPersonDataMustBeSavedAndReturn() {

        rootRepository.setFullPath("C:\\Users\\Samuel\\Documents\\JAVA\\safetynet\\src\\main\\java\\com\\openclassrooms\\safetynet\\data\\dataTest.json");
        rootRepository.reload();
        rootRepository.addPerson(newPerson);

        assertEquals(newPerson, rootRepository.getPersonByName("Watson").get(0));

        //Removing the
        rootRepository.removePerson(newPerson);
        assertTrue(rootRepository.getPersonByName("Watson").isEmpty());
    }

    @Test
    public void testSavingPerson_returnAnError() {
        when(rootRepository.)
    }

    @Test
    public void testDeleting_methodMustFindTheDataAndDeleteIt() {

    }


}
