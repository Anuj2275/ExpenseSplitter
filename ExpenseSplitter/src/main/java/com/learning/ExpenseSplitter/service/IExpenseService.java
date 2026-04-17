package com.learning.ExpenseSplitter.service;

import com.learning.ExpenseSplitter.dto.ExpenseResponse;
import com.learning.ExpenseSplitter.model.Expense;

import java.math.BigDecimal;
import java.util.List;

public interface IExpenseService {
    ExpenseResponse createExpense(Long userId, BigDecimal amount, String description);
    List<ExpenseResponse> getExpensesByUserId(Long userId);
}
