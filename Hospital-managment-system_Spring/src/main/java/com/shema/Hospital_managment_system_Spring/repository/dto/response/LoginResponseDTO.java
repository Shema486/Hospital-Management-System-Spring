package com.shema.Hospital_managment_system_Spring.repository.dto.response;

import com.shema.Hospital_managment_system_Spring.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private Long userId;
    private String username;
    private Role role;
    private String fullName;
}
