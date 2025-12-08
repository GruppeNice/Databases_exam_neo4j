package com.neo4jbackend.service;

import com.neo4jbackend.dto.DoctorRequest;
import com.neo4jbackend.model.Doctor;
import com.neo4jbackend.model.Ward;
import com.neo4jbackend.repository.DoctorRepository;
import com.neo4jbackend.repository.WardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final WardRepository wardRepository;

    public  DoctorService(DoctorRepository doctorRepository, WardRepository wardRepository) {
        this.doctorRepository = doctorRepository;
        this.wardRepository = wardRepository;
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public void save(DoctorRequest request){
        Ward ward = wardRepository.findById(request.getWardId()).orElse(null);
        Doctor doctor = new Doctor();
        doctor.setWard(ward);
        doctor.setDoctorName(request.getDoctorName());
        doctor.setSpeciality(request.getSpeciality());
        doctorRepository.save(doctor);
    }
}