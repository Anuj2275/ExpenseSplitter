package com.learning.ExpenseSplitter.service;

import com.learning.ExpenseSplitter.model.User;
import org.springframework.stereotype.Service;

public interface IUserService {

    User createUser(String name,String email,String password);
    User getUserById(Long id);
    User login(String name, String password);

}
