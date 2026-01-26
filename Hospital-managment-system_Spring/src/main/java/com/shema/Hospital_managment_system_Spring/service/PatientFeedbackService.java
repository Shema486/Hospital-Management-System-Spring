package com.shema.Hospital_managment_system_Spring.service;

import com.shema.Hospital_managment_system_Spring.entity.PatientFeedback;
import com.shema.Hospital_managment_system_Spring.exception.BadRequestException;
import com.shema.Hospital_managment_system_Spring.exception.NotFoundException;
import com.shema.Hospital_managment_system_Spring.repository.PatientFeedbackDao;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.FeedbackRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.FeedbackResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PatientFeedbackService {
    private final PatientFeedbackDao feedbackDAO ;

    public FeedbackResponseDTO addFeedback(FeedbackRequestDTO feedback) {
        if (feedback == null){
            throw  new BadRequestException("feedback is required");
        }
        PatientFeedback patientFeedback = new PatientFeedback();
        patientFeedback.setPatient(feedback.getPatient());
        patientFeedback.setFeedbackDate(feedback.getFeedbackDate());
        patientFeedback.setComments(feedback.getComments());
        patientFeedback.setRating(feedback.getRating());
        feedbackDAO.addFeedback(patientFeedback);
        return mapToResponse(patientFeedback);

    }

    public List<FeedbackResponseDTO> getAllFeedback() {
        List<PatientFeedback> feedbackList = feedbackDAO.findAll();

        if (feedbackList.isEmpty()){
            throw new NotFoundException("No feedback found");
        }
        return feedbackList.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void deleteFeedback(Long id) {
        PatientFeedback patientFeedback = feedbackDAO.findById(id);
        if (patientFeedback ==null){
            throw  new NotFoundException("feedback not found with ID"+id);
        }
        feedbackDAO.deleteFeedback(id);

    }

   public FeedbackResponseDTO mapToResponse(PatientFeedback patientFeedback){
        return new FeedbackResponseDTO(
                patientFeedback.getFeedbackId(),
                patientFeedback.getPatient(),
                patientFeedback.getRating(),
                patientFeedback.getComments(),
                patientFeedback.getFeedbackDate()
        );
   }

}