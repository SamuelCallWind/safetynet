package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FloodResponse;
import com.openclassrooms.safetynet.dto.HouseholdDto;
import com.openclassrooms.safetynet.dto.PersonDto;
import com.openclassrooms.safetynet.dto.StationDto;
import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.util.DateUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodService {

    @Autowired
    RootRepository rootRepository;

    public FloodResponse getFloodResponse(List<Integer> stations) {
        List<HouseholdDto> houseHolds = new ArrayList<>();
        List<StationDto> stationDtos = new ArrayList<>();
        for (Integer station : stations) {
            List<PersonDto> personDtos = new ArrayList<>();
            PersonService.getPersonsByFirestationNumber(station, rootRepository)
                    .forEach(person -> {
                        personDtos.add(new PersonDto(person.getFirstName(), person.getLastName(),
                                MedicalRecordService.getMedicationFromFullName(person.getFirstName(), person.getLastName(), rootRepository),
                                MedicalRecordService.getAllergiesFromFullName(person.getFirstName(), person.getLastName(), rootRepository),
                                person.getPhone(),
                                DateUtils.calculateAge(MedicalRecordService.getAgeFromPersonsName(person.getFirstName(), person.getLastName(), rootRepository))));
                    });
            houseHolds.add(new HouseholdDto(personDtos));
            stationDtos.add(new StationDto(FirestationService.getFirestationAddressFromNumber(station, rootRepository) ,houseHolds));
        }
        return new FloodResponse(stationDtos);
    }
}
