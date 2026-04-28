package com.example.Budgify_Api.controllers;

import com.example.Budgify_Api.services.budget.BudgetService;
import com.example.Budgify_Api.services.budget.dtos.BudgetDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @PostMapping
    public BudgetDTO.Response createBudget(@RequestBody BudgetDTO.CreateRequest request) {
        return budgetService.createBudget(request);
    }

    @GetMapping("/user/{userId}")
    public List<BudgetDTO.Response> getUserBudgets(@PathVariable("userId") Long userId) {
        return budgetService.getUserBudgets(userId);
    }
}
