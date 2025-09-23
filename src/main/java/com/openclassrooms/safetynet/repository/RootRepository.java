package com.openclassrooms.safetynet.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.model.Root;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@Data
public class RootRepository {

    private Root root;
    private ObjectMapper mapper = new ObjectMapper();
    private String FILE_NAME = "data.json";
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
            log.error("File data.json not found, reading from the original file in resources.");
            try {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
                if (inputStream == null) {
                    System.out.println("Error while reading the value from the data.json file");
                }
                root = mapper.readValue(inputStream, Root.class);
            } catch (IOException err) {
                log.error("Error while reading the value from the data.json file: ", err);
            }
        }

    }


    public List<Person> getPersonByName(String name) {
        List<Person> result = new ArrayList<>();
        try {
            root.getPersons().forEach(person -> {
                if (person.getLastName().equals(name)) {
                    result.add(person);
                }
            });
        } catch (RuntimeException e) {
            log.error("Error while getting the person by last name: {}", name, e);
        }
        return result;
    }

    public void addPerson(Person person) {
        try {
            root.getPersons().add(person);
            save();
        } catch (RuntimeException e) {
            log.error("Error while adding the following person: {}", person);
        }
    }

    private void save() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(fullPath), root);
        } catch (Exception e) {
            log.error("Failed to save the data: {}", e.getMessage());
        }
        reload();
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public void removePerson(Person person) {
        try {
            root.getPersons().remove(person);
            save();
        } catch (RuntimeException e) {
            log.error("Failed to remove the person: {}", person);
        }
    }

    public void modifyPerson(Person dataToBeChanged) {
        try {
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
        } catch (RuntimeException e) {
            log.error("Failed to modify the person: {}", dataToBeChanged);
        }
    }

    public void addFirestation(Firestation firestation) {
        try {
            root.getFirestations().add(firestation);
            save();
        } catch (RuntimeException e) {
            log.error("Failed to add the firestation: {}", firestation);
        }
    }

    public void removeFirestation(Firestation firestation) {
        boolean removed = root.getFirestations().remove(firestation);

        if (!removed) {
            log.error("There was an error while trying to delete the firestation: {} (Not found)", firestation);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        save();
    }

    public void modifyFirestation(Firestation firestation) {
        Optional<Firestation> result = root.getFirestations().stream()
                .filter(station -> station.getAddress().equals(firestation.getAddress()))
                .findFirst();
        if (result.isPresent()) {
            result.get().setStation(firestation.getStation());
            save();
        } else {
            log.error("Failed to modify the firestation: {}",firestation);
            throw new RuntimeException("Failed to modify the firestation: " + firestation + " (not found)");
        }
    }

    public void addMedicalRecord(Medicalrecord medicalrecord) {
        try {
            root.getMedicalrecords().add(medicalrecord);
            save();
        } catch (RuntimeException e) {
            log.error("Failed to add the medical record: {}", medicalrecord, e);
        }
    }

    public void removeMedicalRecord(String firstName, String lastName) {
        Medicalrecord recordToBeRemoved = root.getMedicalrecords().stream()
                .filter(record -> record.getFirstName().equals(firstName) && record.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
        if (recordToBeRemoved == null) {
            log.error("Unable to delete the medical record of: {} {}, as it is not found in the file", firstName, lastName);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Medical record not found for: " + firstName + " " + lastName);
        } else {
            root.getMedicalrecords().remove(recordToBeRemoved);
            save();
        }
    }

    public void modifyMedicalRecord(Medicalrecord medicalrecord) {
        try {
            for (int i = 0; i < root.getMedicalrecords().size(); ++i) {
                Medicalrecord currentRecord = root.getMedicalrecords().get(i);
                if (currentRecord.getFirstName().equals(medicalrecord.getFirstName())
                        && currentRecord.getLastName().equals(medicalrecord.getLastName())) {
                    currentRecord.setBirthdate(medicalrecord.getBirthdate());
                    currentRecord.setMedications(medicalrecord.getMedications());
                    currentRecord.setAllergies(medicalrecord.getAllergies());
                    save();
                    return;
                }
            }
            throw new RuntimeException();
        } catch (RuntimeException e) {
            log.error("Failed to modify the medical record: {}", medicalrecord, e);
        }
    }
}
