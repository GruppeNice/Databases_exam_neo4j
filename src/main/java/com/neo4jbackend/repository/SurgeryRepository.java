package com.neo4jbackend.repository;

import com.neo4jbackend.model.Surgery;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface SurgeryRepository extends Neo4jRepository<Surgery, UUID> {
}

