package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FloodResponse;
import com.openclassrooms.safetynet.dto.HouseholdDto;
import com.openclassrooms.safetynet.dto.FloodPersonDto;
import com.openclassrooms.safetynet.dto.StationDto;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodService {

    @Autowired
    RootRepository rootRepository;

    FloodService() {}
    FloodService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public FloodResponse getFloodResponse(List<Integer> stations) {
        List<HouseholdDto> houseHolds = new ArrayList<>();
        List<StationDto> stationDtos = new ArrayList<>();
        for (Integer station : stations) {
            List<FloodPersonDto> floodPersonDtos = new ArrayList<>();
            PersonService.getPersonsByFirestationNumber(station, rootRepository)
                    .forEach(person -> {
                        floodPersonDtos.add(new FloodPersonDto(person.getFirstName(), person.getLastName(),
                                MedicalRecordService.getMedicationFromFullName(person.getFirstName(), person.getLastName(), rootRepository),
                                MedicalRecordService.getAllergiesFromFullName(person.getFirstName(), person.getLastName(), rootRepository),
                                person.getPhone(),
                                DateUtils.calculateAge(MedicalRecordService.getBirthdateFromPersonsName(person.getFirstName(), person.getLastName(), rootRepository))));
                    });
            houseHolds.add(new HouseholdDto(floodPersonDtos));
            stationDtos.add(new StationDto(FirestationService.getFirestationAddressFromNumber(station, rootRepository) ,houseHolds));
        }
        return new FloodResponse(stationDtos);
    }
}
