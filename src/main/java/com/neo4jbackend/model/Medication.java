package com.example.hospital_db_backend.model.neo4j;

import com.example.hospital_db_backend.model.entity_bases.MedicationBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

@Getter
@Setter
@Node
public class Medication extends MedicationBase {
    @Id
    private UUID medicationId  = UUID.randomUUID();
}
