package com.example.Budgify_Api.config;

import com.example.Budgify_Api.entities.Category;
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
            saveCategory("Food", "fast-food-outline", "#FF6B6B");
            saveCategory("Transport", "car-outline", "#4D96FF");
            saveCategory("Entertainment", "game-controller-outline", "#6BCB77");
            saveCategory("Rent", "home-outline", "#FFD93D");
            saveCategory("Shopping", "cart-outline", "#92A9BD");
        }
    }

    private void saveCategory(String name, String icon, String color) {
        Category category = new Category();
        category.setName(name);
        category.setIcon(icon);
        category.setColor(color);
        categoryRepository.save(category);
    }
}