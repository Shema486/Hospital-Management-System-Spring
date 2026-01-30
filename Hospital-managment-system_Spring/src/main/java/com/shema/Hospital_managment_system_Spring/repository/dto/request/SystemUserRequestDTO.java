package com.shema.Hospital_managment_system_Spring.repository.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shema.Hospital_managment_system_Spring.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SystemUserRequestDTO {
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "full names required ")
    @JsonProperty("fullName")
    private String fullName;

    @NotBlank(message = "password is required")
    @Size(min =  8,message = "at least 8 characters")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;
}
