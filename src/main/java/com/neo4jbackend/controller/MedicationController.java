package com.neo4jbackend.controller;

import com.neo4jbackend.dto.MedicationRequest;
import com.neo4jbackend.model.Medication;
import com.neo4jbackend.service.MedicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neo4j/medications")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Medication>> getMedications() {
        List<Medication> medications = medicationService.findAll();
        if (medications.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(medications, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createMedication(@RequestBody MedicationRequest request) {
        medicationService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}