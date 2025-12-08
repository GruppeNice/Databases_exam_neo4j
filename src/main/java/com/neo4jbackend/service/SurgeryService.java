package com.neo4jbackend.service;

import com.neo4jbackend.dto.SurgeryRequest;
import com.neo4jbackend.model.Doctor;
import com.neo4jbackend.model.Patient;
import com.neo4jbackend.model.Surgery;
import com.neo4jbackend.repository.DoctorRepository;
import com.neo4jbackend.repository.PatientRepository;
import com.neo4jbackend.repository.SurgeryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurgeryService {

    private final SurgeryRepository surgeryRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public SurgeryService(SurgeryRepository surgeryRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.surgeryRepository = surgeryRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public List<Surgery> findAll() {
        return surgeryRepository.findAll();
    }

    public void save(SurgeryRequest request){
        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElse(null);
        Patient patient =  patientRepository.findById(request.getPatientId()).orElse(null);
        Surgery surgery = new Surgery();
        surgery.setDoctor(doctor);
        surgery.setPatient(patient);
        surgery.setSurgeryDate(request.getSurgeryDate());
        surgery.setDescription(request.getDescription());
        surgeryRepository.save(surgery);
    }
}