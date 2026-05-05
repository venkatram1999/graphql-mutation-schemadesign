package com.mutationschemadesign.graphql.registerandloginproject.service;


import com.mutationschemadesign.graphql.registerandloginproject.model.Account;
import com.mutationschemadesign.graphql.registerandloginproject.model.UserRole;
import com.mutationschemadesign.graphql.registerandloginproject.model.UserStatus;
import com.mutationschemadesign.graphql.registerandloginproject.model.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> userList = new ArrayList<>();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService() {
        userList.add(new User(1, "admin", passwordEncoder.encode("admin123"), UserRole.ADMIN, UserStatus.ACTIVE));
        userList.add(new User(2, "venkat", passwordEncoder.encode("venkat123"), UserRole.USER, UserStatus.ACTIVE));
        userList.add(new User(3, "testuser", passwordEncoder.encode("test123"), UserRole.GUEST, UserStatus.INACTIVE));
    }

    public List<User> getAllUsers() {
        List<User> responseList = new ArrayList<>();

        for (User user : userList) {
            responseList.add(new User(
                    user.getId(),
                    user.getUsername(),
                    null,
                    user.getRole(),
                    user.getStatus()
            ));
        }

        return responseList;
    }

    public User getUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return new User(
                        user.getId(),
                        user.getUsername(),
                        null,
                        user.getRole(),
                        user.getStatus()
                );
            }
        }
        return null;
    }

    public List<Account> getAllProfiles() {
        List<Account> profiles = new ArrayList<>();

        profiles.add(new Admin(100, "superadmin", UserRole.ADMIN, 1));
        profiles.add(new GuestUser(200, "guestuser", UserRole.GUEST, "Temporary portal access"));

        for (User user : userList) {
            profiles.add(new User(
                    user.getId(),
                    user.getUsername(),
                    null,
                    user.getRole(),
                    user.getStatus()
            ));
        }

        return profiles;
    }

    public Object register(RegisterInput input) {
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(input.getUsername())) {
                return new AuthError("Username already exists", "USER_ALREADY_EXISTS");
            }
        }

        int newId = userList.size() + 1;

        User newUser = new User(
                newId,
                input.getUsername(),
                passwordEncoder.encode(input.getPassword()),
                input.getRole(),
                UserStatus.ACTIVE
        );

        userList.add(newUser);

        return new AuthSuccess(
                "User registered successfully",
                new User(
                        newUser.getId(),
                        newUser.getUsername(),
                        null,
                        newUser.getRole(),
                        newUser.getStatus()
                )
        );
    }

    public Object login(LoginInput input) {
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(input.getUsername())) {

                boolean matched = passwordEncoder.matches(input.getPassword(), user.getPassword());

                if (matched) {
                    return new AuthSuccess(
                            "Login successful",
                            new User(
                                    user.getId(),
                                    user.getUsername(),
                                    null,
                                    user.getRole(),
                                    user.getStatus()
                            )
                    );
                } else {
                    return new AuthError("Invalid password", "INVALID_PASSWORD");
                }
            }
        }

        return new AuthError("User not found", "USER_NOT_FOUND");
    }

    public User updateUser(UpdateUserInput input) {
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(input.getUsername())) {

                if (input.getNewUsername() != null && !input.getNewUsername().isBlank()) {
                    user.setUsername(input.getNewUsername());
                }

                if (input.getNewPassword() != null && !input.getNewPassword().isBlank()) {
                    user.setPassword(passwordEncoder.encode(input.getNewPassword()));
                }

                if (input.getRole() != null) {
                    user.setRole(input.getRole());
                }

                if (input.getStatus() != null) {
                    user.setStatus(input.getStatus());
                }

                return new User(
                        user.getId(),
                        user.getUsername(),
                        null,
                        user.getRole(),
                        user.getStatus()
                );
            }
        }
        return null;
    }

    public String deleteUser(String username) {
        boolean removed = userList.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
        return removed ? "User deleted successfully" : "User not found";
    }
}