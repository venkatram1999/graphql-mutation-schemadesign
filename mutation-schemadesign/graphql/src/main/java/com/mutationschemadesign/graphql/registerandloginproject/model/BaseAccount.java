package com.mutationschemadesign.graphql.registerandloginproject.model;

public abstract class BaseAccount implements Account {
    protected Integer id;
    protected String username;
    protected UserRole role;

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

}
