package com.example.Budgify_Api.services.budget;

import com.example.Budgify_Api.entities.*;
import com.example.Budgify_Api.repos.*;
import com.example.Budgify_Api.services.budget.dtos.BudgetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public BudgetDTO.Response createBudget(BudgetDTO.CreateRequest request) {
        Optional<Budget> existingBudget = budgetRepository.findByUserIdAndCategoryIdAndMonthAndYear(
                request.getUserId(),
                request.getCategoryId(),
                request.getMonth(),
                request.getYear()
        );

        Budget budget;

        if (existingBudget.isPresent()) {
            budget = existingBudget.get();
            budget.setTotalAmount(request.getTotalAmount());
        } else {
            budget = new Budget();

            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            budget.setUser(user);
            budget.setCategory(category);
            budget.setMonth(request.getMonth());
            budget.setYear(request.getYear());
            budget.setSpentAmount(0.0);
            budget.setTotalAmount(request.getTotalAmount());
        }

        Budget saved = budgetRepository.save(budget);
        return convertToResponse(saved);
    }

    public List<BudgetDTO.Response> getUserBudgets(Long userId) {
        return budgetRepository.findByUserId(userId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private BudgetDTO.Response convertToResponse(Budget budget) {
        BudgetDTO.Response response = new BudgetDTO.Response();
        response.setId(budget.getId());
        response.setUserId(budget.getUser() != null ? budget.getUser().getId() : null);
        response.setCategoryId(budget.getCategory() != null ? budget.getCategory().getId() : null);
        response.setTotalAmount(budget.getTotalAmount());
        response.setSpentAmount(budget.getSpentAmount());
        response.setRemainingAmount(budget.getTotalAmount() - budget.getSpentAmount());

        if (budget.getCategory() != null) {
            response.setCategoryName(budget.getCategory().getName());
            response.setCategoryIcon(budget.getCategory().getIcon());
            response.setCategoryColor(budget.getCategory().getColor());
        }

        response.setMonth(budget.getMonth());
        response.setYear(budget.getYear());
        return response;
    }
}
