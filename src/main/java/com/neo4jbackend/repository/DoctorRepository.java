package com.neo4jbackend.repository;

import com.neo4jbackend.model.Doctor;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface DoctorRepository extends Neo4jRepository<Doctor, UUID> {
}

