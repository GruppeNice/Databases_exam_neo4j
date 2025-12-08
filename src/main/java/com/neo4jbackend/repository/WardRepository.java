package com.neo4jbackend.repository;

import com.neo4jbackend.model.Ward;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface WardNeo4jRepository extends Neo4jRepository<Ward, UUID> {
}
