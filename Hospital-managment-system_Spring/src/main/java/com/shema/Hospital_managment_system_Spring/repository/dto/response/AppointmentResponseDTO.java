package com.shema.Hospital_managment_system_Spring.repository.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO {
    private Long appointmentId;

    private Long patientId;
    private String patient;

    private Long doctorId;
    private String doctor;

    @JsonFormat(pattern = "yyyy-MM-dd 'T' HH:mm")
    private LocalDateTime appointmentDate;

    private String status;
    private String reason;

}
