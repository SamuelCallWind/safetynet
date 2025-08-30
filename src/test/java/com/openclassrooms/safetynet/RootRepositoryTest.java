package com.openclassrooms.safetynet;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RootRepositoryTest {


    @Test
    public void testSaving_newPersonDataMustBeSavedAndReturn() {
        Person newPerson = new Person();
        newPerson.setFirstName("John");
        newPerson.setLastName("Watson");
        newPerson.setAddress("221 baker st");
        newPerson.setCity("London");
        newPerson.setPhone("555-555-555");
        newPerson.setZip(50000);
        newPerson.setEmail("johnwatsontest@email.com");


        RootRepository rootRepository = new RootRepository();
        rootRepository.setFullPath("C:\\Users\\Samuel\\Documents\\JAVA\\safetynet\\src\\main\\java\\com\\openclassrooms\\safetynet\\data\\dataTest.json");
        rootRepository.reload();
        rootRepository.addPerson(newPerson);
        rootRepository.reload();
        assertNotNull(rootRepository.getPersonByName("Watson"));
    }

}
