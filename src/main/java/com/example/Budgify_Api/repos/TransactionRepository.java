package com.example.Budgify_Api.repos;

import com.example.Budgify_Api.entities.Transaction;
import com.example.Budgify_Api.enums.GlobalEnums;
import com.example.Budgify_Api.services.overview.dtos.OverviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserIdOrderByDateDesc(Long userId);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId " +
            "AND t.financeType = :type AND MONTH(t.date) = :month AND YEAR(t.date) = :year")
    Double sumAmountByType(@Param("userId") Long userId,
                           @Param("type") GlobalEnums.FinanceType type,
                           @Param("month") int month,
                           @Param("year") int year);

    @Query("SELECT new com.example.Budgify_Api.services.overview.dtos.OverviewDTO$CategorySummaryDTO(c.name, SUM(t.amount), c.color) " +
            "FROM Transaction t JOIN t.category c " +
            "WHERE t.user.id = :userId AND t.financeType = :expenseType " +
            "AND MONTH(t.date) = :month AND YEAR(t.date) = :year " +
            "GROUP BY c.name, c.color")
    List<OverviewDTO.CategorySummaryDTO> getCategorySummary(@Param("userId") Long userId,
                                                            @Param("expenseType") GlobalEnums.FinanceType expenseType,
                                                            @Param("month") int month,
                                                            @Param("year") int year);
}