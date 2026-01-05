package com.neo4jbackend;

import com.neo4jbackend.model.types.AppointmentStatusType;
import com.neo4jbackend.model.types.DoctorSpecialityType;
import com.neo4jbackend.model.types.NurseSpecialityType;
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
        seedMedications(20);
        seedHospitals(10);
        seedWards(8);
        seedDoctors(10);
        seedNurses(15);
        seedPatients(30);
        seedDiagnoses(20);
        seedPrescriptions(30);
        seedSurgeries(20);
        seedAppointments(30);

        System.out.println("Entity seeding with enums complete!");
    }

    private void seedMedications(int count) {
        for (int i = 0; i < count; i++) {
            String medicationId = UUID.randomUUID().toString();
            String medicationName = faker.medical().medicineName();
            String dosage = (i % 3 + 1) * 100 + " mg";

            if(medicationName.contains("'")){
                continue;
            }

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

            if(hospitalName.contains("'")){
                continue;
            }

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

    private void seedDoctors(int count) {
        for (int i = 0; i < count; i++) {
            String doctorId = UUID.randomUUID().toString();
            String doctorName = faker.name().fullName();
            String speciality = getRandomEnum(DoctorSpecialityType.class).name();

            if(doctorName.contains("'")){
                continue;
            }

            neo4jClient.query(
                    String.format(
                            "CREATE (d:Doctor {doctorId: '%s', name: '%s', speciality: '%s'})",
                            doctorId, doctorName, speciality
                    )
            ).run();
        }
    }

    // Seed Nurses
    private void seedNurses(int count) {
        for (int i = 0; i < count; i++) {
            String nurseId = UUID.randomUUID().toString();
            String nurseName = faker.name().fullName();

            if(nurseName.contains("'")){
                continue;
            }
            String speciality = getRandomEnum(NurseSpecialityType.class).name();

            neo4jClient.query(
                    String.format(
                            "CREATE (n:Nurse {nurseId: '%s', name: '%s', speciality: '%s'})",
                            nurseId, nurseName, speciality
                    )
            ).run();
        }
    }

    // Seed Patients and Assign Relationships
    private void seedPatients(int count) {
        for (int i = 0; i < count; i++) {
            String patientId = UUID.randomUUID().toString();
            String patientName = faker.name().fullName();

            if(patientName.contains("'")){
                continue;
            }

            // Assign a random doctor
            neo4jClient.query(
                    String.format(
                            "MATCH (d:Doctor) " +
                                    "WITH d " +
                                    "WHERE rand() < 0.25 " + // Assign to a random doctor 25% of the time
                                    "CREATE (p:Patient {patientId: '%s', name: '%s'})-[:TREATED_BY]->(d)",
                            patientId, patientName
                    )
            ).run();
        }
    }

    private void seedDiagnoses(int count) {
        for (int i = 0; i < count; i++) {
            String diagnosisId = UUID.randomUUID().toString();
            String description = faker.medical().symptoms();

            // Link to random patient and doctor
            neo4jClient.query(
                    String.format(
                            "MATCH (p:Patient) WITH p LIMIT 1 " +
                                    "MATCH (d:Doctor) WITH p, d LIMIT 1 " +
                                    "WHERE rand() < 0.2 " + // Randomly select a doctor + patient 20% of the time
                                    "CREATE (dx:Diagnosis {diagnosisId: '%s', description: '%s'})-[:RELATED_TO]->(p), " +
                                    "(dx)-[:CONFIRMED_BY]->(d)",
                            diagnosisId, description
                    )
            ).run();
        }
    }

    private void seedPrescriptions(int count) {
        for (int i = 0; i < count; i++) {
            String prescriptionId = UUID.randomUUID().toString();
            String startDate = faker.date().past(100, java.util.concurrent.TimeUnit.DAYS).toString();
            String endDate = faker.date().future(30, java.util.concurrent.TimeUnit.DAYS).toString();

            neo4jClient.query(
                    String.format(
                            "MATCH (p:Patient) WITH p LIMIT 1 " +
                                    "MATCH (d:Doctor) WITH p, d LIMIT 1 " +
                                    "MATCH (m:Medication) WITH p, d, m LIMIT 1 " +
                                    "WHERE rand() < 0.2 " + // Randomly select 20% of combinations
                                    "CREATE (pr:Prescription {prescriptionId: '%s', startDate: '%s', endDate: '%s'})-[:PRESCRIBED_TO]->(p), " +
                                    "(pr)-[:PRESCRIBED_BY]->(d), (pr)-[:CONTAINS]->(m)",
                            prescriptionId, startDate, endDate
                    )
            ).run();
        }
    }

    // Seed Surgeries and Link to Patients + Doctors
    private void seedSurgeries(int count) {
        for (int i = 0; i < count; i++) {
            String surgeryId = UUID.randomUUID().toString();
            String surgeryDate = faker.date().past(100, java.util.concurrent.TimeUnit.DAYS).toString();

            neo4jClient.query(
                    String.format(
                            "MATCH (p:Patient) WITH  p LIMIT 1 " +
                                    "MATCH (d:Doctor) WITH p, d LIMIT 1 " +
                                    "WHERE rand() < 0.2 " + // Randomly select 20% of patients and doctors
                                    "CREATE (s:Surgery {surgeryId: '%s', surgeryDate: '%s'})-[:PERFORMED_ON]->(p), " +
                                    "(s)-[:PERFORMED_BY]->(d)",
                            surgeryId, surgeryDate
                    )
            ).run();
        }
    }

    private void seedAppointments(int count) {
        for (int i = 0; i < count; i++) {
            String appointmentId = UUID.randomUUID().toString();
            String status = getRandomEnum(AppointmentStatusType.class).name();
            String appointmentDate = faker.date().future(30, java.util.concurrent.TimeUnit.DAYS).toString();

            neo4jClient.query(
                    String.format(
                            "MATCH (p:Patient) WHERE rand() < 0.5 WITH p LIMIT 1 " +
                                    "MATCH (d:Doctor) WHERE rand() < 0.5 WITH p, d LIMIT 1 " +
                                    "MATCH (n:Nurse) WHERE rand() < 0.5 WITH p, d, n LIMIT 1 " +
                                    "CREATE (a:Appointment {appointmentId: '%s', status: '%s', appointmentDate: '%s'})-[:ASSIGNED_TO]->(p), " +
                                    "(a)-[:BOOKED_WITH]->(d), (a)-[:ASSISTED_BY]->(n)",
                            appointmentId, status, appointmentDate
                    )
            ).run();
        }
    }



    private <T extends Enum<?>> T getRandomEnum(Class<T> enumClass) {
        T[] enumConstants = enumClass.getEnumConstants();
        return enumConstants[random.nextInt(enumConstants.length)];
    }
}