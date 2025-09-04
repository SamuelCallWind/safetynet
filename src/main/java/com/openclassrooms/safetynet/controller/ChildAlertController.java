package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.ChildAlertResponse;
import com.openclassrooms.safetynet.repository.RootRepository;
import com.openclassrooms.safetynet.service.ChildAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChildAlertController {

    @Autowired
    ChildAlertService childAlertService;

    @GetMapping("/childAlert")
    public ChildAlertResponse getChildAlert(@RequestParam String address) {
        return childAlertService.getChildAlert(address);
    }
}
