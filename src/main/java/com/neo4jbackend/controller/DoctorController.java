package com.neo4jbackend.controller;

import com.neo4jbackend.dto.DoctorRequest;
import com.neo4jbackend.model.Doctor;
import com.neo4jbackend.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getDoctors() {
        List<Doctor> doctors = doctorService.findAll();
        if (doctors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createDoctor(@RequestBody DoctorRequest request) {
        doctorService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}