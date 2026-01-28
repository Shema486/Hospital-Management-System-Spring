package com.shema.Hospital_managment_system_Spring.controller;

import com.shema.Hospital_managment_system_Spring.entity.SystemUser;
import com.shema.Hospital_managment_system_Spring.repository.SystemUserDao;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.LoginRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.SystemUserRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.LoginResponseDTO;
import com.shema.Hospital_managment_system_Spring.service.SystemUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class SystemUserController {
    private SystemUserService userService;

    @PostMapping("/register")
    public ResponseEntity<SystemUser> register(
            @Valid @RequestBody SystemUserRequestDTO requestDTO
            ){
        return ResponseEntity.ok(userService.registerUser(requestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        LoginResponseDTO response = userService.login(requestDTO.getUsername(), requestDTO.getPassword());
        return ResponseEntity.ok(response);
    }

}
