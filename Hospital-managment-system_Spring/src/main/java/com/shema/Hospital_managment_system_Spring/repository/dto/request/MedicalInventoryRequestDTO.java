package com.shema.Hospital_managment_system_Spring.repository.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicalInventoryRequestDTO {

    @NotBlank(message = "item is required")
    private String itemName;
    @NotNull(message = "stock Quantity is required")
    @Min(value = 1,message = "price cannot be negative ")
    private int StockQuantity;
    @NotNull(message = "unit price is required")
    @Min(value = 1,message = "Quantity cannot be negative ")
    private double unitPrice;
}
