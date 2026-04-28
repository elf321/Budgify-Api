package com.example.Budgify_Api.services.category;

import com.example.Budgify_Api.entities.Category;
import com.example.Budgify_Api.enums.CategoryType;
import com.example.Budgify_Api.repos.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    public List<Category> getCategoriesByType(String type) {
        return categoryRepository.findByType(type.toUpperCase());
    }

    public Category saveCategory(Category category) {

        return categoryRepository.save(category);
    }
}
