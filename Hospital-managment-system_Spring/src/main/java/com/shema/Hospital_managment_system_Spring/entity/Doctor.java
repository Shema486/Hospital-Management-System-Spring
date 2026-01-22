package com.shema.Hospital_managment_system_Spring.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private int doctorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phone;
    private int department;

}
