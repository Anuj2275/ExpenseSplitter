package com.learning.ExpenseSplitter.exception;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
