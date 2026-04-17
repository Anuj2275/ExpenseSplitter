package com.learning.ExpenseSplitter.controller;

import com.learning.ExpenseSplitter.dto.CreateExpenseRequest;
import com.learning.ExpenseSplitter.dto.ExpenseResponse;
import com.learning.ExpenseSplitter.model.Expense;
import com.learning.ExpenseSplitter.service.IExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {


    private final IExpenseService service;

//    @PostMapping
//    public ResponseEntity<ExpenseResponse> createExpense(@RequestBody CreateExpenseRequest request){
////        Expense expense = service.createExpense(request.getUserId(), request.getAmount(),request.getDescription());
////
////        return ResponseEntity.ok(expense);
//        return ResponseEntity.ok(
//                service.createExpense(
//                        request.getUserId(),
//                        request.getAmount(),
//                        request.getDescription()
//                )
//        );
//
//    }
@PostMapping
public ResponseEntity<ExpenseResponse> createExpense(@Valid @RequestBody CreateExpenseRequest request) {

    return ResponseEntity.ok(
            service.createExpense(
                    request.getUserId(),
                    request.getAmount(),
                    request.getDescription()
            )
    );
}

//    @GetMapping("/user/{userid}")
//    public ResponseEntity<List<ExpenseResponse>> getExpensesByUser(@PathVariable Long userId){
////        List<Expense> expenses = service.getExpensesByUserId(userId);
////
////        return ResponseEntity.ok(expenses);
//        return ResponseEntity.ok(
//                service.getExpensesByUserId(userId)
//        );
//    }
@GetMapping("/user/{userId}")
public ResponseEntity<List<ExpenseResponse>> getExpensesByUser(@PathVariable Long userId) {

    return ResponseEntity.ok(
            service.getExpensesByUserId(userId)
    );
}

}
