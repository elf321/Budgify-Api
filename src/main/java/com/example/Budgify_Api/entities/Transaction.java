package com.example.Budgify_Api.entities;

import com.example.Budgify_Api.enums.GlobalEnums;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GlobalEnums.FinanceType financeType;

    private String description;

    private Double amount;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
}