package com.learning.ExpenseSplitter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;

}
