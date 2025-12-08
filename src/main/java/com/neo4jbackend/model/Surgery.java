package com.example.hospital_db_backend.model.neo4j;

import com.example.hospital_db_backend.model.entity_bases.SurgeryBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;

@Getter
@Setter
@Node
public class Surgery extends SurgeryBase {
    @Id
    private UUID surgeryId = UUID.randomUUID();
    @Relationship(type = "HAS_SURGERY", direction = Relationship.Direction.OUTGOING)
    private Patient patient;
    @Relationship(type = "OPERATED_BY", direction = Relationship.Direction.OUTGOING)
    private Doctor doctor;
}
