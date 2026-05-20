package com.example.Budgify_Api.services.overview;

import com.example.Budgify_Api.enums.GlobalEnums;
import com.example.Budgify_Api.repos.TransactionRepository;
import com.example.Budgify_Api.services.overview.dtos.OverviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OverviewService {

    private final TransactionRepository transactionRepository;

    public OverviewDTO.Overview getOverview(Long userId) {
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        Double monthlyIncome = transactionRepository.sumAmountByType(
                userId,
                GlobalEnums.FinanceType.INCOME,
                month,
                year
        );

        Double monthlyExpenses = transactionRepository.sumAmountByType(
                userId,
                GlobalEnums.FinanceType.EXPENSE,
                month,
                year
        );

        monthlyIncome = (monthlyIncome != null) ? monthlyIncome : 0.0;
        monthlyExpenses = (monthlyExpenses != null) ? monthlyExpenses : 0.0;

        List<OverviewDTO.CategorySummaryDTO> breakdown = transactionRepository.getCategorySummary(
                userId,
                GlobalEnums.FinanceType.EXPENSE,
                month,
                year
        );

        return OverviewDTO.Overview.builder()
                .monthlyIncome(monthlyIncome)
                .monthlyExpenses(monthlyExpenses)
                .totalBalance(monthlyIncome - monthlyExpenses)
                .categoryBreakdown(breakdown)
                .build();
    }
}