package com.revature.controllers;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    // Insert a new User
    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user){
        User u = userDAO.save(user);

        return ResponseEntity.status(201).body(u);
    }

    // Update an existing User
    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody String username){
        Optional<User> u = userDAO.findById(userId);

        if(u.isEmpty()){
            return ResponseEntity.status(404).body("No user found with ID " + userId);
        }

        // extracting the User and updating the username
        User user = u.get();
        user.setUsername(username);
        userDAO.save(user);

        return ResponseEntity.accepted().body(user);
    }
}