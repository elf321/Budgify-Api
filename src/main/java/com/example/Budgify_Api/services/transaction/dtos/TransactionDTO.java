package com.example.Budgify_Api.services.transaction.dtos;

import lombok.Data;

public class TransactionDTO {
    @Data
    public static class CreateRequest {
        private String description;
        private Double amount;
        private Long categoryId;
        private Long userId;
    }

    @Data
    public static class Response {
        private Long id;
        private String description;
        private Double amount;
        private String categoryName;
        private String categoryColor;
        private String date;
    }
}
