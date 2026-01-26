package com.shema.Hospital_managment_system_Spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalInventory {
 private Long itemId;
 private String itemName;
 private int StockQuantity;
 private double unitPrice;


}
