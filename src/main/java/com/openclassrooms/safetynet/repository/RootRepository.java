package com.openclassrooms.safetynet.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Root;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
@Data
public class RootRepository {

    private Root root;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String FILE_NAME = "data.json";
    private String fullPath = "C:\\Users\\Samuel\\Documents\\JAVA\\safetynet\\src\\main\\java\\com\\openclassrooms\\safetynet\\data\\data.json";

    @PostConstruct
    public void initialize() {
        reload();
    }


    public void reload() {
        try {
            root = mapper.readValue(new File(fullPath), Root.class);
            return;
        } catch (Exception e) {
            System.out.println("File data.json not found, reading from the original file in resources. \n");
        }
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
            if (inputStream == null) {
                System.out.println("Error while reading the value from the data.json file");
            }
            root = mapper.readValue(inputStream, Root.class);
        } catch (IOException e) {
            System.out.println("Error while reading the value from the data.json file: " + e);
        }
    }


    public List<Person> getPersonByName(String name) {
        List<Person> result = new ArrayList<>();
        root.getPersons().forEach(person -> {
            if (person.getLastName().equals(name)) {
                result.add(person);
            }
        });
        return result;
    }

    public void addPerson(Person person) {
        root.getPersons().add(person);
        save();
    }

    private void save() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(fullPath), root);
        } catch (Exception e) {
            System.out.println("Failed to save the data: " + e.getMessage());
        }
        reload();
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public void removePerson(Person person) {
        root.getPersons().remove(person);
        save();
    }

    public void modifyPerson(Person dataToBeChanged) {
        List<Person> persons = root.getPersons();
        for (int i = 0; i < persons.size(); ++i) {
            Person currentPerson = persons.get(i);
            if (currentPerson.getFirstName().equals(dataToBeChanged.getFirstName()) && currentPerson.getLastName().equals(dataToBeChanged.getLastName())) {
                currentPerson.setAddress(dataToBeChanged.getAddress());
                currentPerson.setCity(dataToBeChanged.getCity());
                currentPerson.setPhone(dataToBeChanged.getPhone());
                currentPerson.setEmail(dataToBeChanged.getEmail());
                currentPerson.setZip(dataToBeChanged.getZip());
                break;
            }
        }
        save();
    }

    public void addFirestation(Firestation firestation) {
        root.getFirestations().add(firestation);
        save();
    }

    public void removeFirestation(Firestation firestation) {
        try {
            root.getFirestations().remove(firestation);
        } catch (Exception e) {
            System.out.println("There was an error while trying to delete the firestation" + e.getMessage());
        }
        save();
    }
}
