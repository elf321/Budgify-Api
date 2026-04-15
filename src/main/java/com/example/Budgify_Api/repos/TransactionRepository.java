package com.example.Budgify_Api.repos;

import com.example.Budgify_Api.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByUserIdOrderByDateDesc(Long userId);
}
