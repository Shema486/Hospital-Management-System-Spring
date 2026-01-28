package com.shema.Hospital_managment_system_Spring.service;

import com.shema.Hospital_managment_system_Spring.entity.Role;
import com.shema.Hospital_managment_system_Spring.entity.SystemUser;
import com.shema.Hospital_managment_system_Spring.exception.BadRequestException;
import com.shema.Hospital_managment_system_Spring.repository.SystemUserDao;
import com.shema.Hospital_managment_system_Spring.repository.dto.request.SystemUserRequestDTO;
import com.shema.Hospital_managment_system_Spring.repository.dto.response.LoginResponseDTO;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Transactional
 public class SystemUserService {
    private SystemUserDao userDao;

    public SystemUser registerUser (SystemUserRequestDTO dto){
           if(userDao.findByUsername(dto.getUsername()) !=null){
               throw new BadRequestException("username already exist");
           }

        Role role = Role.valueOf(dto.getRole().name().toUpperCase());
        SystemUser user = new SystemUser();
           user.setUsername(dto.getUsername());
           user.setFullName(dto.getFullName());
           user.setPassword(PasswordUtil.hashPassword(dto.getPassword()));
           user.setRole(role);
           user.setIsActive(true);
           user.setCreatedAt(LocalDateTime.now());
           user.setUpdatedAt(LocalDateTime.now());

        userDao.addUser(user);
           return  user;
    }

    public LoginResponseDTO login(String username, String password) {
        SystemUser user = userDao.findByUsername(username);

        if (user == null || !user.getIsActive())
            throw new BadRequestException("Invalid credentials");

        if (!PasswordUtil.checkPassword(password, user.getPassword()))
            throw new BadRequestException("Invalid credentials");


        return new LoginResponseDTO(user.getUsername(), user.getRole(), user.getFullName());
    }

}
