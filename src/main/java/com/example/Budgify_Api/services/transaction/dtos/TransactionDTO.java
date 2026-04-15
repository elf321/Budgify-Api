package com.example.Budgify_Api.services.transaction.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class TransactionDTO {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{
        private String description;
        private Double amount;
        private Long categoryId;
        private Long userId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class View {
        private Long id;
        private String description;
        private Double amount;
        private String categoryName;
        private String categoryColor;
        private String date;
    }
}
