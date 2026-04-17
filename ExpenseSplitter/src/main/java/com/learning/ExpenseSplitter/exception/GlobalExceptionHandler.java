package com.learning.ExpenseSplitter.exception;

import com.learning.ExpenseSplitter.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class) // user not found
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex){
        return ResponseEntity.status(404)
                .body(new ErrorResponse(ex.getMessage(),404));
    }

    @ExceptionHandler(UserAlreadyExistsException.class) // duplicate email
    public ResponseEntity<ErrorResponse> handleUserExists(UserAlreadyExistsException ex){
        return ResponseEntity.status(409)
                .body(new ErrorResponse(ex.getMessage(),409));
    }
    @ExceptionHandler(InvalidAmountException.class) // invalid amount
    public ResponseEntity<ErrorResponse> handleInValidAmount(InvalidAmountException ex){
//        older  return ResponseEntity.badRequest().body(ex.getMessage());
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(ex.getMessage(),400));
    }


//    This runs automatically when:
//You use @Valid in a controller
//AND validation fails on request data

//    VERY IMPORTANT ***************8
    /* VERY IMPORTANT ***********************
Focus on these 3 things only:
1️⃣ When it is used
👉 When @Valid fails → this exception is thrown

2️⃣ What it returns
👉 A clean error response like:
{
  "field": "error message"
}
3️⃣ What it’s doing conceptually
👉
Get validation errors
Extract field + message
Return them in a map
    * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}
