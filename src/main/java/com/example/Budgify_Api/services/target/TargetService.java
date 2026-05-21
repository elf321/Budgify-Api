package com.example.Budgify_Api.services.target;

import com.example.Budgify_Api.entities.Target;
import com.example.Budgify_Api.entities.Transaction;
import com.example.Budgify_Api.enums.GlobalEnums;
import com.example.Budgify_Api.enums.GlobalEnums.TargetType;
import com.example.Budgify_Api.repos.TargetRepository;
import com.example.Budgify_Api.repos.TransactionRepository;
import com.example.Budgify_Api.services.target.dtos.TargetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TargetService {

    private final TargetRepository targetRepository;
    private final TransactionRepository transactionRepository;

    public List<TargetDTO.StatusView> getTargetStatusList(Long userId) {
        List<Target> targets = targetRepository.findByUserId(userId);
        List<TargetDTO.StatusView> statusList = new ArrayList<>();

        for (Target target : targets) {
            LocalDateTime start = target.getStartDate().atStartOfDay();
            LocalDateTime end = target.getEndDate().atTime(23, 59, 59);

            List<Transaction> periodExpenses = transactionRepository
                    .findByUserIdAndDateBetween(userId, start, end)
                    .stream()
                    .filter(t -> t.getFinanceType() == GlobalEnums.FinanceType.EXPENSE)
                    .toList();

            double spent = calculateSpent(target, periodExpenses);
            double targetAmount = target.getTargetAmount() != null ? target.getTargetAmount() : 0.0;
            double remaining = targetAmount - spent;
            double percentage = targetAmount > 0 ? (spent / targetAmount) * 100.0 : 0.0;
            if (percentage > 100.0) {
                percentage = 100.0;
            }

            statusList.add(TargetDTO.StatusView.builder()
                    .id(target.getId())
                    .targetType(target.getTargetType())
                    .categoryName(target.getCategoryName())
                    .categoryColor(target.getCategoryColor())
                    .targetAmount(targetAmount)
                    .currentSpent(spent)
                    .remainingAmount(remaining)
                    .progressPercentage(Math.round(percentage * 100.0) / 100.0)
                    .build());
        }

        return statusList;
    }

    private double calculateSpent(Target target, List<Transaction> expenses) {
        if (target.getTargetType() == TargetType.GENERAL) {
            return expenses.stream().mapToDouble(Transaction::getAmount).sum();
        }

        if (target.getTargetType() == TargetType.CATEGORY) {
            String categoryName = target.getCategoryName();
            if (categoryName == null || categoryName.isBlank()) {
                return 0.0;
            }
            return expenses.stream()
                    .filter(t -> t.getCategory() != null
                            && categoryName.equalsIgnoreCase(t.getCategory().getName()))
                    .mapToDouble(Transaction::getAmount)
                    .sum();
        }

        return 0.0;
    }

    public Target createTarget(Long userId, TargetDTO.Create dto) {
        if (dto.getTargetType() == TargetType.CATEGORY
                && (dto.getCategoryName() == null || dto.getCategoryName().isBlank())) {
            throw new IllegalArgumentException("Category name is required for CATEGORY targets");
        }
        if (dto.getTargetAmount() == null || dto.getTargetAmount() <= 0) {
            throw new IllegalArgumentException("Target amount must be greater than zero");
        }

        Target target = new Target();
        target.setUserId(userId);
        target.setTargetType(dto.getTargetType());
        target.setCategoryName(dto.getCategoryName());
        target.setCategoryColor(dto.getCategoryColor());
        target.setTargetAmount(dto.getTargetAmount());
        target.setStartDate(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
        target.setEndDate(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));

        return targetRepository.save(target);
    }
}
