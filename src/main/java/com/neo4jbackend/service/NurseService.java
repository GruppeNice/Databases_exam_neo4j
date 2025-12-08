package com.neo4jbackend.service;

import com.neo4jbackend.dto.NurseRequest;
import com.neo4jbackend.model.Nurse;
import com.neo4jbackend.model.Ward;
import com.neo4jbackend.repository.NurseRepository;
import com.neo4jbackend.repository.WardRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class NurseService {

    private final NurseRepository nurseRepository;
    private final WardRepository wardRepository;

    public NurseService(NurseRepository nurseRepository, WardRepository wardRepository) {
        this.nurseRepository = nurseRepository;
        this.wardRepository = wardRepository;
    }

    public List<Nurse> findAll() {
        return nurseRepository.findAll();
    }

    public void  save(@RequestBody NurseRequest request) {
        Ward ward = wardRepository.findById(request.getWardId()).orElse(null);
        Nurse nurse = new Nurse();
        nurse.setNurseName(request.getNurseName());
        nurse.setSpeciality(request.getSpeciality());
        nurse.setWard(ward);
        nurseRepository.save(nurse);
    }
}