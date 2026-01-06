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
        seedWards(8);
        seedHospitals(10);
        seedDoctors(10);
        seedNurses(15);
        seedDiagnoses(20);
        seedPatients(30);
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
                            "MATCH (w:Ward) WHERE rand() < 0.5 WITH COLLECT(w) AS wards " +
                                    "CREATE (h:Hospital {hospitalId: '%s', hospitalName: '%s'}) WITH h, wards " +
                                    "UNWIND wards AS w " +
                                    "CREATE (h)-[:HAS_WARD]->(w)",
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
                            "CREATE (w:Ward {wardId: '%s', wardName: '%s', type: '%s'})",
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
                            "CREATE (d:Doctor {doctorId: '%s', doctorName: '%s', speciality: '%s'})",
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
                            "CREATE (n:Nurse {nurseId: '%s', nurseName: '%s', speciality: '%s'})",
                            nurseId, nurseName, speciality
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
                            "MATCH (d:Doctor) WHERE rand() < 0.2  WITH d LIMIT 1 " +
                                    "CREATE (dx:Diagnosis {diagnosisId: '%s', description: '%s'})<-[:GIVEN_BY]-(d)",
                            diagnosisId, description
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

            neo4jClient.query(
                    String.format(
                            "MATCH (w:Ward) WHERE rand() < 0.25 WITH w LIMIT 1 " +
                                    "MATCH (h:Hospital) WHERE rand() < 0.25 WITH w, h LIMIT 1 " +
                                    "MATCH (d:Diagnosis) WHERE rand() < 0.25 WITH w, h, d " +
                                    "CREATE (p:Patient {patientId: '%s', patientName: '%s'})-[:ASSIGNED_TO]->(w)," +
                                    "(p)-[:HAS_HOSPITAL]->(h), (p)-[:HAS_DIAGNOSIS]->(d)",
                            patientId, patientName
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
                            "MATCH (p:Patient) WHERE rand() < 0.2  WITH p LIMIT 1 " +
                                    "MATCH (d:Doctor) WHERE rand() < 0.2  WITH p, d LIMIT 1 " +
                                    "MATCH (m:Medication) WHERE rand() < 0.2  WITH p, d, m LIMIT 1 " +
                                    "CREATE (pr:Prescription {prescriptionId: '%s', startDate: '%s', endDate: '%s'})-[:PRESCRIBED_TO]->(p), " +
                                    "(pr)-[:PRESCRIBED_BY]->(d), (pr)-[:PRESCRIPTION_OF]->(m)",
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
                            "MATCH (p:Patient) WHERE rand() < 0.2  WITH  p LIMIT 1 " +
                                    "MATCH (d:Doctor) WHERE rand() < 0.2  WITH p, d LIMIT 1 " +
                                    "CREATE (s:Surgery {surgeryId: '%s', surgeryDate: '%s'})-[:HAS_SURGERY]->(p), " +
                                    "(s)-[:OPERATED_BY]->(d)",
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
                                    "CREATE (a:Appointment {appointmentId: '%s', status: '%s', appointmentDate: '%s'})-[:APPOINTED_FOR]->(p), " +
                                    "(a)-[:ASSIGNED_DOCTOR]->(d), (a)-[:ASSIGNED_NURSE]->(n)",
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