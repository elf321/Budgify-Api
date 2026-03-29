package com.example.Budgify_Api.repos;

import com.example.Budgify_Api.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long> {
}
