package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {

    @Autowired
    RootRepository rootRepository;

    public static int getFirestationNumberFromAddress(String address, RootRepository rootRepository) {
        Firestation firestation = rootRepository.getRoot().getFirestations().stream()
                .filter(f -> f.getAddress().equals(address))
                .findAny().orElse(null);
        return (firestation != null) ? firestation.getStation() : -1;
    }

    public static String getFirestationAddressFromNumber(Integer stationNumber, RootRepository rootRepository) {
        Firestation firestation = rootRepository.getRoot().getFirestations().stream()
                .filter(f -> f.getStation() == stationNumber)
                .findAny().orElse(null);
        return (firestation != null) ? firestation.getAddress() : "Not found";
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
