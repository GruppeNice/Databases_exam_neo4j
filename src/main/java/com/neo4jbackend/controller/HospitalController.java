package com.neo4jbackend.controller;

import com.neo4jbackend.dto.HospitalRequest;
import com.neo4jbackend.model.Hospital;
import com.neo4jbackend.service.HospitalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neo4j/hospitals")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hospital>> getHospitals() {
        List<Hospital> hospitals = hospitalService.findAll();
        if (hospitals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Hospital> createHospital(@RequestBody HospitalRequest request) {
        hospitalService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}