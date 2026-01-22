package com.shema.Hospital_managment_system_Spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientFeedback {

    private int feedbackId;
    private int patient;
    private int rating;
    private String comments;
    private LocalDate feedbackDate;


}

