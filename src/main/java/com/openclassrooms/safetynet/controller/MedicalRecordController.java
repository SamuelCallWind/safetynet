package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.NameDto;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @PostMapping("/medicalRecord")
    public void addMedicalRecord(Medicalrecord medicalrecord) {
        medicalRecordService.addMedicalRecord(medicalrecord);
    }

    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecord(@RequestBody NameDto dto) {
        medicalRecordService.removeMedicalRecord(dto.getFirstName(), dto.getLastName());
    }
}
