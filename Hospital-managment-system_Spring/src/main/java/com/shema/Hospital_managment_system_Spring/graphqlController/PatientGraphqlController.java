package com.shema.Hospital_managment_system_Spring.graphqlController;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.PatientRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.PatientResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.shema.Hospital_managment_system_Spring.service.PatientService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class PatientGraphqlController {
    private PatientService patientService;

    @QueryMapping
    public List<PatientResponseDTO> patients() {
        return patientService.getAllPatients();
    }
    @QueryMapping
    public PatientResponseDTO patientById(@Argument Long id) {
        return patientService.getPatientById(id);
    }
    @QueryMapping
    public List<PatientResponseDTO> searchByName(@Argument String lastName) {
        return patientService.searchPatientByLastName(lastName);
    }
    @MutationMapping
    public PatientResponseDTO addPatient(@Valid @Argument PatientRequestDTO input) {
        return patientService.addPatient(input);
    }
    @MutationMapping
    public Long deletePatient(@Argument Long id){
         patientService.deletePatient(id);
         return id;
    }
    @MutationMapping
    public PatientResponseDTO updatePatient(@Argument Long id,
            @Valid @Argument("input") PatientRequestDTO dto) {
        return patientService.updatePatient(id, dto);
    }



}
