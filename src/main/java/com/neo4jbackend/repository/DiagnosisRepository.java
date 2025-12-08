package com.neo4jbackend.repository;

import com.neo4jbackend.model.Diagnosis;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface DiagnosisRepository extends Neo4jRepository<Diagnosis, UUID> {
}

