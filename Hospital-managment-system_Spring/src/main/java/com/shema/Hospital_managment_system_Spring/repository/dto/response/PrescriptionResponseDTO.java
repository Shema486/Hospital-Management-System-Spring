package com.shema.Hospital_managment_system_Spring.repository.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionResponseDTO {
    private Long prescriptionId;
    private Long appointmentId;
    @JsonFormat(pattern = "yyyy-MM-dd 'T' HH:mm")
    private LocalDateTime prescriptionDate;
    private String notes;
}
