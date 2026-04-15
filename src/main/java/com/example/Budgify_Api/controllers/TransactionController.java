package com.example.Budgify_Api.controllers;


import com.example.Budgify_Api.entities.Transaction;
import com.example.Budgify_Api.services.transaction.TransactionService;
import com.example.Budgify_Api.services.transaction.dtos.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/add")
    public ResponseEntity<TransactionDTO.View> addTransaction(@RequestBody TransactionDTO.Create request) {
        return ResponseEntity.ok(transactionService.createTransaction(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionDTO.View>> getTransactions(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getUserTransactions(userId));
    }
}
