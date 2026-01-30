package com.shema.Hospital_managment_system_Spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private Long appointmentId;
    private Long patientId;
    private String patient;
    private Long doctorId;
    private String doctor;
    private LocalDateTime appointmentDate;
    private String status;
    private String reason;
}
