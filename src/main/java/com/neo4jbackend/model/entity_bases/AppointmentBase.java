package com.neo4jbackend.model.entity_bases;

import com.neo4jbackend.model.types.AppointmentStatusType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public abstract class AppointmentBase {
    protected LocalDate appointmentDate;
    protected String reason;
    protected AppointmentStatusType status;
}
