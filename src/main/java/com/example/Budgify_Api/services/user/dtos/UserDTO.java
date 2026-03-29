package com.example.Budgify_Api.services.user.dtos;

import lombok.Data;

public class UserDTO {

    @Data
    public static class CreateRequest {
        private String username;
        private String password;
        private String email;
        private String name;
        private String surname;
    }

    @Data
    public static class Response {
        private Long id;
        private String username;
        private String email;
        private String fullName;
    }
}
