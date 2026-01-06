package com.neo4jbackend.controller;

import com.neo4jbackend.dto.PatientRequest;
import com.neo4jbackend.model.Patient;
import com.neo4jbackend.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neo4j/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.findAll();
        if(patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPatient(@RequestBody PatientRequest request) {
        patientService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}