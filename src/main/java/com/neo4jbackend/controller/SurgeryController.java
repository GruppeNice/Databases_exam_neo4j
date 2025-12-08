package com.neo4jbackend.controller;

import com.neo4jbackend.dto.SurgeryRequest;
import com.neo4jbackend.model.Surgery;
import com.neo4jbackend.service.SurgeryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neo4j/surgeries")
public class SurgeryController {

    private final SurgeryService surgeryService;

    public SurgeryController(SurgeryService surgeryService) {
        this.surgeryService = surgeryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Surgery>> getAllSurgeries() {
        List<Surgery> surgeries = surgeryService.findAll();
        if(surgeries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(surgeries, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createSurgery(@RequestBody SurgeryRequest request) {
        surgeryService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}