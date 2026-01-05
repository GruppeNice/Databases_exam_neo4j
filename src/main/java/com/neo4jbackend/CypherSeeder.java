package com.neo4jbackend;

import com.neo4jbackend.model.types.WardType;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class CypherSeeder implements CommandLineRunner {

    private final Neo4jClient neo4jClient;
    private final Faker faker = new Faker();
    private final Random random = new Random();
    public CypherSeeder(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    @Override
    public void run(String... args) {
        seedMedications(50);
        seedHospitals(10);
        seedWards(20);

        System.out.println("Entity seeding with enums complete!");
    }

    private void seedMedications(int count) {
        for (int i = 0; i < count; i++) {
            String medicationId = UUID.randomUUID().toString();
            String medicationName = faker.medical().medicineName();
            String dosage = (i % 3 + 1) * 100 + " mg";

            neo4jClient.query(
                    String.format(
                            "CREATE (m:Medication {medicationId: '%s', medicationName: '%s', dosage: '%s'})",
                            medicationId, medicationName, dosage
                    )
            ).run();
        }
    }

    private void seedHospitals(int count) {
        for (int i = 0; i < count; i++) {
            String hospitalId = UUID.randomUUID().toString();
            String hospitalName = "Hospital " + faker.company().name();

            neo4jClient.query(
                    String.format(
                            "CREATE (h:Hospital {hospitalId: '%s', name: '%s'})",
                            hospitalId, hospitalName
                    )
            ).run();
        }
    }

    private void seedWards(int count) {
        for (int i = 0; i < count; i++) {
            String wardId = UUID.randomUUID().toString();
            String wardName = "Ward " + (i + 1);

            String wardType = getRandomEnum(WardType.class).name();

            neo4jClient.query(
                    String.format(
                            "CREATE (w:Ward {wardId: '%s', name: '%s', type: '%s'})",
                            wardId, wardName, wardType
                    )
            ).run();
        }
    }



    private <T extends Enum<?>> T getRandomEnum(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        return enumConstants[random.nextInt(enumConstants.length)];
    }
}