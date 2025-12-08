package com.neo4jbackend.model;

import com.neo4jbackend.model.entity_bases.PrescriptionBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;

@Getter
@Setter
@Node
public class Prescription extends PrescriptionBase {
    @Id
    private UUID prescriptionId =  UUID.randomUUID();
    @Relationship(type = "PRESCRIBED_TO", direction = Relationship.Direction.OUTGOING)
    private Patient patient;
    @Relationship(type = "PRESCRIBED_BY",  direction = Relationship.Direction.OUTGOING)
    private Doctor doctor;
    @Relationship(type = "PRESCRIPTION_OF", direction = Relationship.Direction.OUTGOING)
    private Medication medication;
}
