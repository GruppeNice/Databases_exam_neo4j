package com.neo4jbackend.service;

import com.neo4jbackend.dto.PatientRequest;
import com.neo4jbackend.model.Diagnosis;
import com.neo4jbackend.model.Hospital;
import com.neo4jbackend.model.Patient;
import com.neo4jbackend.model.Ward;
import com.neo4jbackend.repository.DiagnosisRepository;
import com.neo4jbackend.repository.HospitalRepository;
import com.neo4jbackend.repository.PatientRepository;
import com.neo4jbackend.repository.WardRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final WardRepository wardRepository;
    private final DiagnosisRepository diagnosisRepository;

    public PatientService(PatientRepository patientRepository, HospitalRepository hospitalRepository, WardRepository wardRepository, DiagnosisRepository diagnosisRepository) {
        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
        this.wardRepository = wardRepository;
        this.diagnosisRepository = diagnosisRepository;
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public void save(PatientRequest patientRequest) {
        Hospital hospital = hospitalRepository.findById(patientRequest.getHospitalId()).orElse(null);
        Ward ward = wardRepository.findById(patientRequest.getWardId()).orElse(null);
        Set<Diagnosis> diagnoses = new  HashSet<>();
        patientRequest.getDiagnosisIds().forEach(diagnosisId -> {
            Optional<Diagnosis> diagnosis = diagnosisRepository.findById(diagnosisId);
            diagnosis.ifPresent(diagnoses::add);
        });
        Patient patient = new Patient();
        patient.setHospital(hospital);
        patient.setWard(ward);
        patient.setDiagnoses(diagnoses);
        patient.setPatientName(patientRequest.getPatientName());
        patient.setGender(patientRequest.getGender());
        patient.setDateOfBirth(patientRequest.getDateOfBirth());
        patientRepository.save(patient);
    }
}