package com.neo4jbackend.model.entity_bases;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class HospitalBase {
    protected String hospitalName;
    protected String address;
    protected String city;
}
