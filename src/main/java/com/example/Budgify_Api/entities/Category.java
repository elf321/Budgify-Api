package com.example.Budgify_Api.entities;

import com.example.Budgify_Api.enums.GlobalEnums.*;
import jakarta.persistence.*;
import lombok.Data;
import com.example.Budgify_Api.enums.CategoryType;

@Entity
@Data
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String icon;
    private String color;

    @Enumerated(EnumType.STRING)
    private FinanceType type;
}
