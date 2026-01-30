package com.shema.Hospital_managment_system_Spring.repository.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedbackRequestDTO {
    @NotNull(message = "Patient ID is required")
    private Long patient;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private int rating;

    @NotBlank(message = "Comments are required")
    private String comments;

    @NotNull(message = "Feedback date is required")
    @PastOrPresent(message = "Feedback date cannot be in the future")
    private LocalDate feedbackDate;
}
