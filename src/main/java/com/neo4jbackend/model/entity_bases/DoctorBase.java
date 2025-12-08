package com.neo4jbackend.model.entity_bases;

import com.neo4jbackend.model.types.DoctorSpecialityType;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class DoctorBase {
    protected String doctorName;
    
    protected DoctorSpecialityType speciality;
}
