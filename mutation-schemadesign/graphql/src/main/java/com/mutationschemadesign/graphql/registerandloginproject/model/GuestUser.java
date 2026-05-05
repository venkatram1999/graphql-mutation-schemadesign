package com.mutationschemadesign.graphql.registerandloginproject.model;


public class GuestUser extends BaseAccount {
    private String visitPurpose;

    public GuestUser(Integer id, String username, UserRole role, String visitPurpose) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.visitPurpose = visitPurpose;
    }

    public GuestUser() {
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }
}