package com.shema.Hospital_managment_system_Spring.repository.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponseDTO {
    private Long feedbackId;
    private Long patient;
//    private String patientName;
    private int rating;
    private String comments;
    private LocalDate feedbackDate;

}
