package com.example.hospital_db_backend.jpa.repository;

import com.example.hospital_db_backend.model.mysql.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
}

