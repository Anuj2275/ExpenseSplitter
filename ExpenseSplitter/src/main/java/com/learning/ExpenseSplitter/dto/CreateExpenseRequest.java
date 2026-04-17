package com.learning.ExpenseSplitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateExpenseRequest {

    @NotNull
    Long userId;

    @NotNull
    @Positive
    BigDecimal amount;

    @NotBlank
    String description;
}
//Entities = database structure
//DTOs = API contract
//
//Why NOT expose entities:
//
//Prevent exposing internal fields (relations, IDs, etc.)
//Avoid infinite recursion (User ↔ Expense)
//Decouple DB from API