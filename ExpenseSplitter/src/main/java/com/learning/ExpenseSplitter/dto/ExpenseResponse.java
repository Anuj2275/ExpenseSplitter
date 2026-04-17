package com.learning.ExpenseSplitter.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExpenseResponse {

    private Long id;
    private BigDecimal amount;
    private String description;

    private Long userId;
    private String userName;
}
