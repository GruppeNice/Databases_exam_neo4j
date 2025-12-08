package com.neo4jbackend.repository;

import com.neo4jbackend.model.Appointment;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface AppointmentRepository extends Neo4jRepository<Appointment, UUID> {
}
