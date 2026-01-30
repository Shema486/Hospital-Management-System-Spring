package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.FeedbackRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.FeedbackResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.PatientFeedbackService;
import com.shema.Hospital_managment_system_Spring.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/feedback")
@Tag(name = "Feedback Management", description = "APIs for managing patient feedbacks")
public class FeedbackController {
    private PatientFeedbackService patientFeedbackService;

    @Operation(
            summary = "Get all feedbacks",
            description = "Retrieve all feedback provided by patients",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved feedbacks"),
                    @ApiResponse(responseCode = "404", description = "No feedback found")
            }
    )
    @GetMapping
    public ResponseEntity<List<FeedbackResponseDTO>> getAllFeedback(){
        return ResponseEntity.ok(patientFeedbackService.getAllFeedback());
    }
    @Operation(
            summary = "Add feedback",
            description = "Submit a new feedback",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Feedback submitted successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> addFeedback(@Valid @RequestBody FeedbackRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientFeedbackService.addFeedback(requestDTO));
    }
    @Operation(
            summary = "Delete feedback",
            description = "Delete feedback by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Feedback deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Feedback not found")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteFeedback(@PathVariable Long id){
        patientFeedbackService.deleteFeedback(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","Feedback deleted successfully");
        return ResponseEntity.ok(response);
    }

}
