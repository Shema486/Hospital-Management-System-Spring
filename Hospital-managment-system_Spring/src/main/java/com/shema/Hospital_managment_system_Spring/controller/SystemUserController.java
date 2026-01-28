package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.entity.SystemUser;

import com.shema.Hospital_managment_system_Spring.repository.dto.request.LoginRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.SystemUserRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.LoginResponseDTO;

import com.shema.Hospital_managment_system_Spring.service.SystemUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
@Tag(name = "System Users Management", description = "APIs for managing system users")
public class SystemUserController {
    private SystemUserService userService;

    @Operation(
            summary = "Add a new user",
            description = "Creates a new system user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping("/register")
    public ResponseEntity<SystemUser> register(
            @Valid @RequestBody SystemUserRequestDTO requestDTO
            ){
        return ResponseEntity.ok(userService.registerUser(requestDTO));
    }

    @Operation(
            summary = "Login to account",
            description = "login to existing account",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User logged in successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        LoginResponseDTO response = userService.login(requestDTO.getUsername(), requestDTO.getPassword());
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get all system users",
            description = "Retrieve all users registered in the system",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
                    @ApiResponse(responseCode = "404", description = "No users found")
            }
    )
    public ResponseEntity<List<SystemUser>> getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }
}
