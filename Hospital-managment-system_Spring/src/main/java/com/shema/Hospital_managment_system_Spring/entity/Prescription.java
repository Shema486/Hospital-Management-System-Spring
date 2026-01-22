package com.shema.Hospital_managment_system_Spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    private int id;
    private int prescriptionId;
    private int appointmentId;
    private LocalDateTime prescriptionDate;
    private String notes;

}
