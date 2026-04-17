package com.learning.ExpenseSplitter.service;

import com.learning.ExpenseSplitter.exception.UserAlreadyExistsException;
import com.learning.ExpenseSplitter.exception.UserNotFoundException;
import com.learning.ExpenseSplitter.model.User;
import com.learning.ExpenseSplitter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(String name, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("User with this email already exists"); // custom errors = clear, domain-specific errors also in better api response, centralized error handling cleaner debugging
        }
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!passwordEncoder.matches(password,user.getPassword()))
            throw new RuntimeException("Invalid email or password");
        user.setPassword(null);
        return user;
    }
}
