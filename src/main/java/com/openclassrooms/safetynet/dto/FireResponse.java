package com.openclassrooms.safetynet.dto;

import com.openclassrooms.safetynet.model.Firestation;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.model.Person;
import lombok.Data;

import java.util.List;

@Data
public class FireResponse {
    List<FirePersonDto> persons;

    public FireResponse(List<FirePersonDto> persons) {
        this.persons = persons;
    }
}
