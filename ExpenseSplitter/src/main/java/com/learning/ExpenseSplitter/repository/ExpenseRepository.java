package com.learning.ExpenseSplitter.repository;

import com.learning.ExpenseSplitter.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends
        JpaRepository<Expense,Long> {

    List<Expense> findByCreatedById(Long userId);
}
