package com.neo4jbackend.controller;

import com.neo4jbackend.dto.AppointmentRequest;
import com.neo4jbackend.model.Appointment;
import com.neo4jbackend.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.findAll();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAppointment(@RequestBody AppointmentRequest request) {
        appointmentService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}