package com.example.Budgify_Api.services.target.dtos;

import com.example.Budgify_Api.enums.GlobalEnums.TargetType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public class TargetDTO {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create {
        private TargetType targetType;
        private String categoryName;
        private String categoryColor;
        private Double targetAmount;
        private Long userId;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StatusView {
        private Long id;
        private TargetType targetType;
        private String categoryName;
        private String categoryColor;
        private Double targetAmount;
        private Double currentSpent;
        private Double remainingAmount;
        private Double progressPercentage;
    }
}