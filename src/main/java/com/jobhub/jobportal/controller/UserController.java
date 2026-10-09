package com.jobhub.jobportal.controller;

import com.jobhub.jobportal.model.User;
import com.jobhub.jobportal.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }
    @PostMapping("/login")
public User loginUser(@RequestBody User user) {

    return userRepository.findByEmailAndPassword(
        user.getEmail(),
        user.getPassword()
    );
}
@GetMapping("/{id}")
public User getUserById(@PathVariable int id) {
    return userRepository.findById(id).orElse(null);
}
}