package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.FeedbackRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.FeedbackResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.PatientFeedbackService;
import com.shema.Hospital_managment_system_Spring.service.PatientService;
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
public class FeedbackController {
    private PatientFeedbackService patientFeedbackService;

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDTO>> getAllFeedback(){
        return ResponseEntity.ok(patientFeedbackService.getAllFeedback());
    }
    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> addFeedback(@Valid @RequestBody FeedbackRequestDTO requestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientFeedbackService.addFeedback(requestDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> deleteFeedback(@PathVariable Long id){
        patientFeedbackService.deleteFeedback(id);
        Map<String,String> response = new HashMap<>();
        response.put("message","Feedback deleted successfully");
        return ResponseEntity.ok(response);
    }

}
