package com.neo4jbackend.controller;

import com.neo4jbackend.dto.WardRequest;
import com.neo4jbackend.model.Ward;
import com.neo4jbackend.service.WardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neo4j/wards")
public class WardController {

    private final WardService wardService;

    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ward>> getAllWards() {
        List<Ward> wards = wardService.findAll();
        if(wards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(wards, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createWard(@RequestBody WardRequest request) {
        wardService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
