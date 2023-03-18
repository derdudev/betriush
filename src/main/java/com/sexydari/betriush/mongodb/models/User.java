package com.sexydari.betriush.mongodb.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

@Document("users")
public class User {
    @Id
    private ObjectId userId;
    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @DBRef(lazy = true)
    private List<UserRoleModel> roles;

    public User(String username, String email, String password, List<UserRoleModel> roles) {
        this.userId = new ObjectId();
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(ObjectId userId, String username, String email, String password, List<UserRoleModel> roles) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public List<UserRoleModel> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleModel> role) {
        this.roles = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
