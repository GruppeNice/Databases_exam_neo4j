package com.neo4jbackend.model;

import com.neo4jbackend.model.entity_bases.MedicationBase;
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
