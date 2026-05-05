package com.mutationschemadesign.graphql.registerandloginproject.model;

public class Admin extends BaseAccount {
    private Integer adminLevel;

    public Admin(Integer id, String username, UserRole role, Integer adminLevel) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.adminLevel = adminLevel;
    }

    public Admin() {
    }

    public Integer getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(Integer adminLevel) {
        this.adminLevel = adminLevel;
    }
}