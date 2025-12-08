package com.example.hospital_db_backend.model.neo4j;

import com.example.hospital_db_backend.model.entity_bases.DiagnosisBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;

@Getter
@Setter
@Node
public class Diagnosis extends DiagnosisBase {
    @Id
    private UUID diagnosisId = UUID.randomUUID();
    @Relationship(type = "GIVEN_BY", direction = Relationship.Direction.OUTGOING)
    private Doctor doctor;
}
