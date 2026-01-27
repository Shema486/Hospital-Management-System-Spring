package com.shema.Hospital_managment_system_Spring.graphqlController;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.DoctorRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.DoctorResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.DoctorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class DoctorGraphqlController {
    private DoctorService doctorService;

    @QueryMapping
    public List<DoctorResponseDTO> doctors() {
        return doctorService.getAllDoctor();
    }
    @QueryMapping
    public DoctorResponseDTO doctorById(@Argument Long id) {
        return doctorService.getDoctorById(id);
    }
    @QueryMapping
    public List<DoctorResponseDTO> doctorBySpecialization(@Argument String specialization) {
        return doctorService.findDoctorsBySpecialization(specialization);
    }
    @MutationMapping
    public DoctorResponseDTO addDoctor(@Valid @Argument DoctorRequestDTO requestDTO) {
        return doctorService.addDoctor(requestDTO);
    }
    @MutationMapping
    public DoctorResponseDTO updateDoctor(@Argument Long id, @Valid @Argument("input") DoctorRequestDTO dto) {
        return doctorService.updateDoctor(id, dto);
    }
    @MutationMapping
    public String deleteDoctor(@Argument Long id) {
        doctorService.deleteDoctor(id);
        return  "Doctor deleted successfully with Id :" + id;
    }
}
