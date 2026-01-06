package com.neo4jbackend.controller;

import com.neo4jbackend.dto.DiagnosisRequest;
import com.neo4jbackend.model.Diagnosis;
import com.neo4jbackend.service.DiagnosisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neo4j/diagnosis")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Diagnosis>> getDiagnoses() {
        List<Diagnosis> diagnoses = diagnosisService.findAll();
        if (diagnoses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(diagnoses, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Diagnosis> createDiagnosis(@RequestBody DiagnosisRequest request) {
        diagnosisService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}