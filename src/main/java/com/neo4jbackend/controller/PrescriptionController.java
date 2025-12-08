package com.neo4jbackend.controller;

import com.neo4jbackend.dto.PrescriptionRequest;
import com.neo4jbackend.model.Prescription;
import com.neo4jbackend.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.findAll();
        if(prescriptions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPrescription(@RequestBody PrescriptionRequest request) {
        prescriptionService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}