package com.neo4jbackend.repository;

import com.neo4jbackend.model.Nurse;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface NurseRepository extends Neo4jRepository<Nurse, UUID> {
}

