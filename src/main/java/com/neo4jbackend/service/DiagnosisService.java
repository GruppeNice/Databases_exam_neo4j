package com.neo4jbackend.service;

import com.neo4jbackend.dto.DiagnosisRequest;
import com.neo4jbackend.model.Diagnosis;
import com.neo4jbackend.model.Doctor;
import com.neo4jbackend.repository.DiagnosisRepository;
import com.neo4jbackend.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final DoctorRepository doctorRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository, DoctorRepository doctorRepository) {
        this.diagnosisRepository = diagnosisRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<Diagnosis> findAll(){
        return diagnosisRepository.findAll();
    }

    public void save(DiagnosisRequest request){
        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElse(null);
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setDoctor(doctor);
        diagnosis.setDiagnosisDate(request.getDiagnosisDate());
        diagnosis.setDescription(request.getDescription());
        diagnosisRepository.save(diagnosis);
    }
}