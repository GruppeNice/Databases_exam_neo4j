package com.neo4jbackend.model;

import com.neo4jbackend.model.entity_bases.NurseBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;

@Getter
@Setter
@Node
public class Nurse extends NurseBase {
    @Id
    private UUID nurseId  = UUID.randomUUID();
    @Relationship(type = "ASSIGNED_TO", direction = Relationship.Direction.OUTGOING)
    private Ward ward;
}
