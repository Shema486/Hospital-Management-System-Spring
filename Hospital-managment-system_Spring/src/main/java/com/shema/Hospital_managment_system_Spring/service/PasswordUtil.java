package com.shema.Hospital_managment_system_Spring.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordUtil {
    //hash password
    public static String hashPassword(String plainPassword){
        return BCrypt.hashpw(plainPassword,BCrypt.gensalt(10));
    }
    //verify
    public static boolean checkPassword(String plainPassword,String hashedPassword){
        return BCrypt.checkpw(plainPassword,hashedPassword);
    }
}
