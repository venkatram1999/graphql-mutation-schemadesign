package com.mutationschemadesign.graphql.registerandloginproject.model;

public class AuthError {

    private String message;
    private String code;

    public AuthError() {
    }

    public AuthError(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }
}