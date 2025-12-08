package com.neo4jbackend.service;

import com.neo4jbackend.dto.PrescriptionRequest;
import com.neo4jbackend.model.Doctor;
import com.neo4jbackend.model.Medication;
import com.neo4jbackend.model.Patient;
import com.neo4jbackend.model.Prescription;
import com.neo4jbackend.repository.DoctorRepository;
import com.neo4jbackend.repository.MedicationRepository;
import com.neo4jbackend.repository.PatientRepository;
import com.neo4jbackend.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, MedicationRepository medicationRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.medicationRepository = medicationRepository;
    }

    public List<Prescription> findAll() {
        return prescriptionRepository.findAll();
    }

    public void save(PrescriptionRequest request) {
        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElse(null);
        Medication medication = medicationRepository.findById(request.getMedicationId()).orElse(null);
        Patient patient = patientRepository.findById(request.getPatientId()).orElse(null);
        Prescription prescription = new Prescription();
        prescription.setDoctor(doctor);
        prescription.setMedication(medication);
        prescription.setPatient(patient);
        prescription.setStartDate(request.getStartDate());
        prescription.setEndDate(request.getEndDate());
        prescriptionRepository.save(prescription);
    }
}