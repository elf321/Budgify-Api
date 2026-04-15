package com.example.Budgify_Api.services.transaction;

import com.example.Budgify_Api.entities.Category;
import com.example.Budgify_Api.entities.Transaction;
import com.example.Budgify_Api.entities.User;
import com.example.Budgify_Api.repos.CategoryRepository;
import com.example.Budgify_Api.repos.TransactionRepository;
import com.example.Budgify_Api.repos.UserRepository;
import com.example.Budgify_Api.services.transaction.dtos.TransactionDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public TransactionDTO.View createTransaction(TransactionDTO.Create dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Transaction transaction = new Transaction();
        transaction.setDescription(dto.getDescription());
        transaction.setAmount(dto.getAmount());
        transaction.setDate(LocalDateTime.now());
        transaction.setUser(user);
        transaction.setCategory(category);

        Transaction saved = transactionRepository.save(transaction);

        return mapToView(saved);
    }

    public List<TransactionDTO.View> getUserTransactions(Long userId) {
        return transactionRepository.findByUserIdOrderByDateDesc(userId)
                .stream()
                .map(this::mapToView)
                .collect(Collectors.toList());
    }

    private TransactionDTO.View mapToView(Transaction t) {
        return TransactionDTO.View.builder()
                .id(t.getId())
                .description(t.getDescription())
                .amount(t.getAmount())
                .date(t.getDate().toString())
                .categoryName(t.getCategory().getName())
                .categoryColor(t.getCategory().getColor())
                .build();
    }
}
