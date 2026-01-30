package com.shema.Hospital_managment_system_Spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private Long patientId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String gender;
    private String contactNumber;
    private String address;

}
