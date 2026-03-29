package com.example.Budgify_Api.entities;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name="budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="total_amount")
    private Double totalAmount;

    @Column(name="spent_amount")
    private Double spentAmount = 0.0;

    private Integer month;

    private Integer year;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
