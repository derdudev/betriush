package com.sexydari.betriush.payload.request;

import com.sexydari.betriush.mongodb.models.UserRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class SignupRequest {
    @NotBlank
    // @Size(min = 3, max = 20)
    private final String username;

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;

    private List<UserRole> roles;


    public SignupRequest(String username, String email, String password, List<UserRole> role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = role;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }
}
