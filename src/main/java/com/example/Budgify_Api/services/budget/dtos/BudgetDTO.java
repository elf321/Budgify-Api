package com.example.Budgify_Api.services.budget.dtos;

import lombok.Data;

public class BudgetDTO {

    @Data
    public static class CreateRequest {
        private Double totalAmount;
        private Integer month;
        private Integer year;
        private Long categoryId;
        private Long userId;
    }

    @Data
    public static class Response {
        private Long id;
        private Long userId;
        private Long categoryId;
        private Double totalAmount;
        private Double spentAmount;
        private Double remainingAmount;
        private String categoryName;
        private String categoryIcon;
        private String categoryColor;
        private Integer month;
        private Integer year;
    }
}
