package com.neo4jbackend.repository;

import com.neo4jbackend.model.Patient;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface PatientRepository extends Neo4jRepository<Patient, UUID> {
}

