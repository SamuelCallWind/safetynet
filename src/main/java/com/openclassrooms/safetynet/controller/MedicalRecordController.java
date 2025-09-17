package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.NameDto;
import com.openclassrooms.safetynet.model.Medicalrecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MedicalRecordController {

    @Autowired
    MedicalRecordService medicalRecordService;

    @PostMapping("/medicalRecord")
    public void addMedicalRecord(@RequestBody Medicalrecord medicalrecord) {
        log.info("POST method received for the medical record: {}", medicalrecord);
        medicalRecordService.addMedicalRecord(medicalrecord);
    }

    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecord(@RequestBody NameDto dto) {
        log.info("DELETE method received for the person: {}", dto);
        medicalRecordService.removeMedicalRecord(dto.getFirstName(), dto.getLastName());
    }

    @PutMapping("/medicalRecord")
    public void modifyMedicalRecord(@RequestBody Medicalrecord medicalrecord) {
        log.info("PUT method received for the medical record: {}", medicalrecord);
        medicalRecordService.modifyMedicalRecord(medicalrecord);
    }
}
