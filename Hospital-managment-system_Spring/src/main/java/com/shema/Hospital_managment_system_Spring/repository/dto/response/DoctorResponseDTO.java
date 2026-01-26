package com.shema.Hospital_managment_system_Spring.repository.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorResponseDTO {
    private  Long doctorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String email;
    private String phone;
    private Long department;
}
