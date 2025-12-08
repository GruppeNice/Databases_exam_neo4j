package com.neo4jbackend.repository;

import com.neo4jbackend.model.Hospital;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface HospitalRepository extends Neo4jRepository<Hospital, UUID> {
}

