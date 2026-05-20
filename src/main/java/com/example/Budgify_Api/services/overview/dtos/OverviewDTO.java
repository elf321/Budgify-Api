package com.example.Budgify_Api.services.overview.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class OverviewDTO {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Overview {
        private Double totalBalance;
        private Double monthlyIncome;
        private Double monthlyExpenses;
        private List<CategorySummaryDTO> categoryBreakdown;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class  CategorySummaryDTO {
        private String name;
        private Double totalAmount;
        private String color;
    }
}