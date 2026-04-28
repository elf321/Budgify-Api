package com.example.Budgify_Api.config;

import com.example.Budgify_Api.entities.Category;
import com.example.Budgify_Api.enums.GlobalEnums;
import com.example.Budgify_Api.repos.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            saveCategory("Food", "fast-food-outline", "#FF6B6B", GlobalEnums.FinanceType.EXPENSE);
            saveCategory("Transport", "car-outline", "#4D96FF", GlobalEnums.FinanceType.EXPENSE);
            saveCategory("Entertainment", "game-controller-outline", "#6BCB77", GlobalEnums.FinanceType.EXPENSE);
            saveCategory("Rent", "home-outline", "#FFD93D", GlobalEnums.FinanceType.EXPENSE);
            saveCategory("Shopping", "cart-outline", "#92A9BD", GlobalEnums.FinanceType.EXPENSE);

            saveCategory("Salary", "cash", "#34C759", GlobalEnums.FinanceType.INCOME);
            saveCategory("Freelance", "laptop", "#AF52DE", GlobalEnums.FinanceType.INCOME);
            saveCategory("Allowance", "wallet", "#FF9500", GlobalEnums.FinanceType.INCOME);
            saveCategory("Debt/Loan", "repeat", "#5856D6", GlobalEnums.FinanceType.INCOME);
            saveCategory("Gift", "gift", "#FF2D55", GlobalEnums.FinanceType.INCOME);
            saveCategory("Investment", "trending-up", "#007AFF", GlobalEnums.FinanceType.INCOME);
        }
    }

    private void saveCategory(String name, String icon, String color, GlobalEnums.FinanceType type) {
        Category category = new Category();
        category.setName(name);
        category.setIcon(icon);
        category.setColor(color);
        category.setType(type);
        categoryRepository.save(category);
    }
}