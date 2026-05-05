package com.mutationschemadesign.graphql.registerandloginproject.model;

public class AuthSuccess {

    private String message;
    private User user;

    public AuthSuccess() {
    }

    public AuthSuccess(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }
}