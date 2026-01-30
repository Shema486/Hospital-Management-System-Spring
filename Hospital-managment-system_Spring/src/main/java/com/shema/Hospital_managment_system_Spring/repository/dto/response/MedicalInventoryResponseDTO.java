package com.shema.Hospital_managment_system_Spring.repository.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MedicalInventoryResponseDTO {
    private Long itemId;
    private String itemName;
    private int StockQuantity;
    private double unitPrice;
}
