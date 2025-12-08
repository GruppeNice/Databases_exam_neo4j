package com.neo4jbackend.model;

import com.neo4jbackend.model.entity_bases.WardBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

@Getter
@Setter
@Node
public class Ward extends WardBase {
    @Id @GeneratedValue
    private UUID wardId =  UUID.randomUUID();
}
