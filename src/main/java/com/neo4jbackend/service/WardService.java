package com.neo4jbackend.service;

import com.neo4jbackend.model.WardRequest;
import com.neo4jbackend.model.Ward;
import com.neo4jbackend.repository.WardRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class WardNeo4jService {

    WardRepository wardRepository;

    public WardNeo4jService(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    public List<Ward> findAll() {
        return wardRepository.findAll();
    }

    public void save(WardRequest ward) {
        Ward newWard = new Ward();
        newWard.setWardId(null);
        newWard.setType(ward.getType());
        newWard.setMaxCapacity(ward.getMaxCapacity());
        wardRepository.save(newWard);
    }
}
