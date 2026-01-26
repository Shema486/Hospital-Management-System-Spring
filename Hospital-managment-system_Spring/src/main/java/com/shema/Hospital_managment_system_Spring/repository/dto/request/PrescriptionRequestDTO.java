package com.shema.Hospital_managment_system_Spring.repository.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionRequestDTO {
    @NotNull(message = "Appointment ID is required")
    private Long appointmentId;

    @NotNull(message = "Prescription date is required")
    @PastOrPresent(message = "Prescription date cannot be in the future")
    private LocalDateTime prescriptionDate;

    @NotBlank(message = "Notes are required")
    private String notes;
}
