package com.neo4jbackend.model.entity_bases;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class MedicationBase {
    protected String medicationName;
    protected String dosage;
}
