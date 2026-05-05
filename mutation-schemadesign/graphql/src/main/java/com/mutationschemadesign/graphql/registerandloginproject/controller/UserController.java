package com.mutationschemadesign.graphql.registerandloginproject.controller;

import com.mutationschemadesign.graphql.registerandloginproject.model.Account;
import com.mutationschemadesign.graphql.registerandloginproject.model.LoginInput;
import com.mutationschemadesign.graphql.registerandloginproject.model.RegisterInput;
import com.mutationschemadesign.graphql.registerandloginproject.model.UpdateUserInput;
import com.mutationschemadesign.graphql.registerandloginproject.model.User;
import com.mutationschemadesign.graphql.registerandloginproject.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public User getUserByUsername(@Argument String username) {
        return userService.getUserByUsername(username);
    }

    @QueryMapping
    public List<Account> getAllProfiles() {
        return userService.getAllProfiles();
    }

    @MutationMapping
    public Object register(@Argument RegisterInput input) {
        return userService.register(input);
    }

    @MutationMapping
    public Object login(@Argument LoginInput input) {
        return userService.login(input);
    }

    @MutationMapping
    public User updateUser(@Argument UpdateUserInput input) {
        return userService.updateUser(input);
    }

    @MutationMapping
    public String deleteUser(@Argument String username) {
        return userService.deleteUser(username);
    }
}