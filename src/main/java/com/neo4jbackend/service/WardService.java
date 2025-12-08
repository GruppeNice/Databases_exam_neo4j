package com.neo4jbackend.service;

import com.neo4jbackend.dto.WardRequest;
import com.neo4jbackend.model.Ward;
import com.neo4jbackend.repository.WardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardService {

    private final WardRepository wardRepository;

    public WardService(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    public List<Ward> findAll() {
        return wardRepository.findAll();
    }

    public void save(WardRequest request) {
        Ward newWard = new Ward();
        newWard.setType(request.getType());
        newWard.setMaxCapacity(request.getMaxCapacity());
        wardRepository.save(newWard);
    }
}
