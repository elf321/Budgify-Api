package com.example.Budgify_Api.services.user.dtos;

import lombok.Data;

public class UserDTO {

    @Data
    public static class UserRegisterRequest {
        private String username;
        private String password;
        private String email;
        private String name;
        private String surname;
    }

    @Data
    public static class UserLoginRequest {
        private String email;
        private String password;
    }

    @Data
    public static class UserResponse {
        private Long id;
        private String username;
        private String email;
        private String fullName;
    }
}
