package com.example.login.dto;

public class UserDto {

    private int userId;
    private String username;
    private String email;
    private String password;
    private String cpf;
    private String role;

    public  UserDto() {
    }

    public UserDto(int userId, String username, String email, String password, String cpf, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cpf='" + cpf + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
