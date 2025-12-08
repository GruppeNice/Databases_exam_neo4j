package com.neo4jbackend.model;

import com.neo4jbackend.model.entity_bases.AppointmentBase;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;
@Getter
@Setter
@Node
public class Appointment extends AppointmentBase {
    @Id
    private UUID appointmentIds = UUID.randomUUID();
    @Relationship(type = "APPOINTED_FOR", direction = Relationship.Direction.OUTGOING)
    private Patient patient;
    @Relationship(type = "ASSIGNED_DOCTOR", direction = Relationship.Direction.OUTGOING)
    private Doctor doctor;
    @Relationship(type = "ASSIGNED_NURSE", direction = Relationship.Direction.OUTGOING)
    private Nurse nurse;

}
