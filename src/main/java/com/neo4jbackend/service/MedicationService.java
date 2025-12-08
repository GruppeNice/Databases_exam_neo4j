package com.neo4jbackend.service;

import com.neo4jbackend.dto.MedicationRequest;
import com.neo4jbackend.model.Medication;
import com.neo4jbackend.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    MedicationRepository medicationRepository;

    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> findAll() {
        return medicationRepository.findAll();
    }

    public void save(MedicationRequest request) {
        Medication medication = new Medication();
        medication.setMedicationName(request.getMedicationName());
        medication.setDosage(request.getDosage());
        medicationRepository.save(medication);
    }
}