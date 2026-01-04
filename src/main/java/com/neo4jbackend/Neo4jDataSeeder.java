package com.neo4jbackend;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Component;

@Component
@Profile("neo4j-seed")
public class Neo4jDataSeeder {

    private final Neo4jClient neo4jClient;

    public Neo4jDataSeeder(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @PostConstruct
    public void seedData(){

    }
}
