package com.learning.ExpenseSplitter.service;

import com.learning.ExpenseSplitter.dto.ExpenseResponse;
import com.learning.ExpenseSplitter.exception.InvalidAmountException;
import com.learning.ExpenseSplitter.exception.UserNotFoundException;
import com.learning.ExpenseSplitter.model.Expense;
import com.learning.ExpenseSplitter.model.User;
import com.learning.ExpenseSplitter.repository.ExpenseRepository;
import com.learning.ExpenseSplitter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService implements IExpenseService {
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;


    @Override
    public ExpenseResponse createExpense(Long userId, BigDecimal amount, String description) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );

//        this is for validation bec.. Invalid data breaks business logic
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be greater than zero");
        }

        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setDescription(description);
        expense.setCreatedBy(user);

//        return expenseRepository.save(expense);
//        changed after building the mapToResponse

        Expense saved = expenseRepository.save(expense);
        return mapToResponse(saved);

    }

    @Override
    public List<ExpenseResponse> getExpensesByUserId(Long userId) {

        return expenseRepository.findByCreatedById(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ExpenseResponse mapToResponse(Expense expense) {

        ExpenseResponse response = new ExpenseResponse();

        response.setId(expense.getId());
        response.setAmount(expense.getAmount());
        response.setDescription(expense.getDescription());

        response.setUserId(expense.getCreatedBy().getId());
        response.setUserName(expense.getCreatedBy().getName());

        return response;
    }
}
