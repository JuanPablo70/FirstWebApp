package com.nexus.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean authenticate(String username, String password) {
        boolean isValidUsername = username.equalsIgnoreCase("admin");
        boolean isValidPassword = password.equalsIgnoreCase("12345678");
        return isValidUsername && isValidPassword;
    }

}
