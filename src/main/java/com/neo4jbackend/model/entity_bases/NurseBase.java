package com.neo4jbackend.model.entity_bases;

import com.neo4jbackend.model.types.NurseSpecialityType;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class NurseBase {
    protected String nurseName;
    protected NurseSpecialityType speciality;
}
