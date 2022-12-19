package com.example.springbootcrudrestfulwebservices.controller;

import com.example.springbootcrudrestfulwebservices.entity.User;
import com.example.springbootcrudrestfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    //get all users
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //get user by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long id) {
        return userService.getUserById(id);
    }
    //create user
    @PostMapping("/saveUser/")
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    //update user

    @PutMapping("/updateUser/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id") long id ) {
        return userService.updateUser(user,id);
    }

    //delete user by id

    @DeleteMapping("deleteUserById/{id}")
    public ResponseEntity<User> deleteUserById(@PathVariable(value = "id") long id) {
        return userService.deleteUserById(id);
    }

}
