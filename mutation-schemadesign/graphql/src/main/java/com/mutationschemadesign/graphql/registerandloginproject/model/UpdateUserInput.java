package com.mutationschemadesign.graphql.registerandloginproject.model;

public class UpdateUserInput {

    private String username;
    private String newUsername;
    private String newPassword;
    private UserRole role;
    private UserStatus status;

    public UpdateUserInput() {
    }

    public UpdateUserInput(String username, String newUsername, String newPassword, UserRole role, UserStatus status) {
        this.username = username;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.role = role;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}