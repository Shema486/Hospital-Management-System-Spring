package com.shema.Hospital_managment_system_Spring.repository.dto.response;

import com.shema.Hospital_managment_system_Spring.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SystemUserResponseDTO {
    private Long userId;
    private String username;
    private String fullName;
    private Role role;
    private boolean isActive;
    private LocalDateTime createdAt;
}
