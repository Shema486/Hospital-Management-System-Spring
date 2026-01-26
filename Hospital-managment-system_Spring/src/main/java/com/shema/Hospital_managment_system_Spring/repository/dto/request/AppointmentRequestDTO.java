package com.shema.Hospital_managment_system_Spring.repository.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentRequestDTO {

    @NotNull(message = "Patient ID is required")
    private Long patientId;

    @NotBlank(message = "Patient name is required")
    private String patient;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;

    @NotBlank(message = "Doctor name is required")
    private String doctor;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date must be in the present or future")
    @JsonFormat(pattern = "yyyy-MM-dd 'T' HH:mm")
    private LocalDateTime appointmentDate;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "Scheduled|Cancelled|Completed")
    private String status;

    @NotBlank(message = "Reason is required")
    private String reason;
}
