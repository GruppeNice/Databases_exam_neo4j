package com.neo4jbackend.model;

import com.neo4jbackend.model.entity_bases.PatientBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Node
public class Patient extends PatientBase {
    @Id
    private UUID patientId  = UUID.randomUUID();
    @Relationship(type = "ASSIGNED_TO", direction = Relationship.Direction.OUTGOING)
    private Ward ward;
    @Relationship(type = "HAS_HOSPITAL",  direction = Relationship.Direction.OUTGOING)
    private Hospital hospital;
    @Relationship(type = "HAS_DIAGNOSES", direction = Relationship.Direction.OUTGOING)
    private Set<Diagnosis> diagnoses;
}
