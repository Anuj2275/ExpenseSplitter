package com.learning.ExpenseSplitter.repository;

import com.learning.ExpenseSplitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
Optional<User> findByEmail(String email); // its optional because database query might return NO result
//    val may or may not exist
}
