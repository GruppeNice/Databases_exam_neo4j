package com.neo4jbackend.model;

import com.neo4jbackend.model.entity_bases.HospitalBase;
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
    @Relationship(type = "HAS_WARD", direction = Relationship.Direction.OUTGOING)
    private Set<Ward> wards;
}
