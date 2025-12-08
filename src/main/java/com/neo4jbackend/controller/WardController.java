package com.neo4jbackend.controller;

import com.neo4jbackend.model.WardRequest;
import com.neo4jbackend.model.Ward;
import com.neo4jbackend.service.WardNeo4jService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neo4j/wards")
public class WardNeo4jController {

    WardNeo4jService wardNeo4jService;

    public WardNeo4jController(WardNeo4jService wardNeo4jService) {
        this.wardNeo4jService = wardNeo4jService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ward>> getAllWards() {
        List<Ward> wards = wardNeo4jService.findAll();
        return new ResponseEntity<>(wards, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<WardRequest> createWard(@RequestBody WardRequest ward) {
        wardNeo4jService.save(ward);
        return new ResponseEntity<>(ward, HttpStatus.CREATED);
    }
}
