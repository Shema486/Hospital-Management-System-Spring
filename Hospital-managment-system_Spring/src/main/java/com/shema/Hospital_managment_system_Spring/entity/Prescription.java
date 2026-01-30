package com.shema.Hospital_managment_system_Spring.entity;


import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    private Long prescriptionId;
    private Long appointmentId;
    private LocalDateTime prescriptionDate;
    private String notes;

}
