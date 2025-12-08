package com.example.hospital_db_backend.model.neo4j;

import com.example.hospital_db_backend.model.entity_bases.HospitalBase;
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
public class Hospital extends HospitalBase {
    @Id
    private UUID hospitalId  = UUID.randomUUID();
    @Relationship(type = "HAS_WARDS", direction = Relationship.Direction.OUTGOING)
    private Set<Ward> wards;
}
