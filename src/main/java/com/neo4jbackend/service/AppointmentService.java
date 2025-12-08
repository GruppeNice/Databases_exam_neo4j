package com.neo4jbackend.service;

import com.neo4jbackend.dto.AppointmentRequest;
import com.neo4jbackend.model.Appointment;
import com.neo4jbackend.model.Doctor;
import com.neo4jbackend.model.Nurse;
import com.neo4jbackend.model.Patient;
import com.neo4jbackend.repository.AppointmentRepository;
import com.neo4jbackend.repository.DoctorRepository;
import com.neo4jbackend.repository.NurseRepository;
import com.neo4jbackend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository  appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, NurseRepository nurseRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.nurseRepository = nurseRepository;
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public void save(AppointmentRequest request){
        Patient patient = patientRepository.findById(request.getPatientId()).orElse(null);
        Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElse(null);
        Nurse nurse = nurseRepository.findById(request.getNurseId()).orElse(null);
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setReason(request.getReason());
        appointment.setStatus(request.getStatus());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setNurse(nurse);
        appointmentRepository.save(appointment);
    }
}