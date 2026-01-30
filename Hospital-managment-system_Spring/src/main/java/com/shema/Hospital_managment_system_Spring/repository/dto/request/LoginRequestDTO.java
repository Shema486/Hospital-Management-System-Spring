package com.shema.Hospital_managment_system_Spring.repository.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min =  8,message = "at least 8 characters")
    private String password;
}
