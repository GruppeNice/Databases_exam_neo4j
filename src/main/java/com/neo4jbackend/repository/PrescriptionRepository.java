package com.neo4jbackend.repository;

import com.neo4jbackend.model.Prescription;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface PrescriptionRepository extends Neo4jRepository<Prescription, UUID> {
}

