package com.example.login.dto;

public class PasswordDto {

    private String oldPass;
    private String newPass;
    private String email;

    public PasswordDto(String oldPass, String newPass, String email) {
        this.oldPass = oldPass;
        this.newPass = newPass;
        this.email = email;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
