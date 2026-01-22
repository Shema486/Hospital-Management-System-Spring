package com.shema.Hospital_managment_system_Spring.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrescriptionItem {

    private Long prescriptionId;
    private Long itemId;
    private String dosageInstruction;
    private int quantityDispensed;


}
