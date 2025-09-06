package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirestationService {


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

}
