package com.shema.Hospital_managment_system_Spring.repository.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrescriptionItemResponseDTO {
    private Long prescriptionId;
    private Long itemId;
//    private String itemName;
    private String dosageInstruction;
    private int quantityDispensed;


}
