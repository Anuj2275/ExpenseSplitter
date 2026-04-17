package com.learning.ExpenseSplitter.controller;

import com.learning.ExpenseSplitter.dto.LoginRequest;
import com.learning.ExpenseSplitter.exception.UserAlreadyExistsException;
import com.learning.ExpenseSplitter.exception.UserNotFoundException;
import com.learning.ExpenseSplitter.model.User;
import com.learning.ExpenseSplitter.repository.UserRepository;
import com.learning.ExpenseSplitter.service.IUserService;
import com.learning.ExpenseSplitter.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController { // Controller - only req and res NO logic or DB work
//    post(create user) and get(get user)

    private final IUserService userService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User req){
        User user = userService.createUser(req.getName(), req.getEmail(),req.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User user = userService.getUserById(id);
       return  ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
//        User user = userService.login(request.getEmail(),request.getPassword());
//        return ResponseEntity.ok(user);
        String token =  jwtService.generateToken(request.getEmail());

        return ResponseEntity.ok(token);    

    }

//    the below was my code ( a lot of mistakes )
//    private final UserRepository userRepository;

//    @PostMapping
//    public void createUser(String name, String email){
//        if(userRepository.findByEmail(email).isPresent()){
//            throw new UserAlreadyExistsException("User already exists.");
//        }
//        User user = new User();
//        user.setName(name);
//        user.setEmail(email);
//
//        userRepository.save(user);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUser(@PathVariable Long id){
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("User not found"));
//        return ResponseEntity.ok(user);
//    }
}
