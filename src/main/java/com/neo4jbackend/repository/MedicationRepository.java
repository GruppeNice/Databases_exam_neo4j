package com.neo4jbackend.repository;

import com.neo4jbackend.model.Medication;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface MedicationRepository extends Neo4jRepository<Medication, UUID> {
}

