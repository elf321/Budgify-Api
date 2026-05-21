package com.example.Budgify_Api.repos;

import com.example.Budgify_Api.entities.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TargetRepository extends JpaRepository<Target, Long> {
    List<Target> findByUserId(Long userId);
}