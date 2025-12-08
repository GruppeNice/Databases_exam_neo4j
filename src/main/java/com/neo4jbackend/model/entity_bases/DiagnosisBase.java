package com.neo4jbackend.model.entity_bases;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public abstract class DiagnosisBase {
    protected LocalDate diagnosisDate;
    protected String description;
}
