package com.openclassrooms.safetynet.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Root;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RootRepository {

    private Root root;
    private final ObjectMapper mapper = new ObjectMapper();
    private final String FILE_NAME = "data.json";
    private String fullPath = "C:\\Users\\Samuel\\Documents\\JAVA\\safetynet\\src\\main\\java\\com\\openclassrooms\\safetynet\\data\\data.json";

    @PostConstruct
    public void initializeDataFirstStart() {

    }

    public void reload() {
        try {
            System.out.println(fullPath);
            root = mapper.readValue(new File(fullPath), Root.class);
            return;
        } catch (Exception e) {
            System.out.println("File data.json not found, reading from the original file in resources. " + e.getMessage());
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

    public List<Medicalrecord> getMedicalRecordByName() {
        return root.getMedicalrecords();
    }

    public Root getRoot() {
        return root;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
    public String getFullPath() {
        return fullPath;
    }
}
