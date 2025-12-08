package com.neo4jbackend.controller;

import com.neo4jbackend.dto.NurseRequest;
import com.neo4jbackend.model.Nurse;
import com.neo4jbackend.service.NurseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurses")
public class NurseController {

    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Nurse>> getNurses() {
        List<Nurse> nurses = nurseService.findAll();
        if(nurses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(nurses, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createNurse(@RequestBody NurseRequest request) {
        nurseService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}