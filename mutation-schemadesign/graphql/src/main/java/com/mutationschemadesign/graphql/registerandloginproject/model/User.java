package com.mutationschemadesign.graphql.registerandloginproject.model;

public class User implements Account {

    private Integer id;
    private String username;
    private String password;
    private UserRole role;
    private UserStatus status;

    public User() {
    }

    public User(Integer id, String username, String password, UserRole role, UserStatus status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public UserRole getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}