package com.shema.Hospital_managment_system_Spring.repository.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrescriptionItemRequestDTO {

    @NotNull(message = "Prescription ID is required")
    private Long prescriptionId;
    @NotNull(message = "Item ID is required")
    private Long itemId;          // e.g., medicine or procedure ID

    @NotBlank(message = "Dosage instruction is required")
    private String dosageInstruction;

    @Min(value = 1, message = "Quantity dispensed must be at least 1")
    private int quantityDispensed;
}
