package com.learning.ExpenseSplitter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotBlank
    private String description;
//    private String createdBy; // don't use this, think who created this  --- user entity right? then use the below one

//    Each Expense is created by ONE user and One User can have MANY expenses
//    Many (Expense) → One (User)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;  // we didn't user Long userId , bec.. JPA manages relationships not just id's


    private LocalDateTime createdAt;
}
