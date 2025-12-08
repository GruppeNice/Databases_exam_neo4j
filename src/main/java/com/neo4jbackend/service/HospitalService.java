package com.neo4jbackend.service;

import com.neo4jbackend.dto.HospitalRequest;
import com.neo4jbackend.model.Hospital;
import com.neo4jbackend.model.Ward;
import com.neo4jbackend.repository.HospitalRepository;
import com.neo4jbackend.repository.WardRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final WardRepository wardRepository;

    public HospitalService(HospitalRepository hospitalRepository, WardRepository wardRepository) {
        this.hospitalRepository = hospitalRepository;
        this.wardRepository = wardRepository;
    }

    public List<Hospital> findAll() {
        return hospitalRepository.findAll();
    }

    public void save(HospitalRequest request){
        Set<Ward> wards = new HashSet<>();
        request.getWardIds().forEach(wardId -> {
            Optional<Ward> ward = wardRepository.findById(wardId);
            ward.ifPresent(wards::add);
        });
        Hospital hospital = new Hospital();
        hospital.setWards(wards);
        hospital.setHospitalName(request.getHospitalName());
        hospital.setCity(request.getCity());
        hospital.setAddress(request.getAddress());
        hospitalRepository.save(hospital);
    }
}