package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FirestationService {

    @Autowired
    RootRepository rootRepository;

    FirestationService() {}
    FirestationService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public static int getFirestationNumberFromAddress(String address, RootRepository rootRepository) {
        try {
            Firestation firestation = rootRepository.getRoot().getFirestations().stream()
                    .filter(f -> f.getAddress().equals(address))
                    .findAny().orElse(null);
            return (firestation != null) ? firestation.getStation() : -1;
        } catch (RuntimeException e) {
            log.error("Failed to get the Firestation number from address: {}", address, e);
        }
        return -1;
    }

    public static String getFirestationAddressFromNumber(Integer stationNumber, RootRepository rootRepository) {
        try {
            Firestation firestation = rootRepository.getRoot().getFirestations().stream()
                    .filter(f -> f.getStation() == stationNumber)
                    .findAny().orElse(null);
            return (firestation != null) ? firestation.getAddress() : "Not found";
        } catch (RuntimeException e) {
            log.error("Failed to get the Firestation address from number: {}", stationNumber, e);
        }
        return "Not found";
    }

    public void addFirestation(Firestation firestation) {
        if (rootRepository.getRoot().getFirestations().contains(firestation)) {
            throw new RuntimeException("The firestation is already inside the database");
        } else {
            rootRepository.addFirestation(firestation);
        }
    }

    public void removeFirestation(Firestation firestation) {
        rootRepository.removeFirestation(firestation);
    }

    public void modifyFirestation(Firestation firestation) {
        rootRepository.modifyFirestation(firestation);
    }
}
