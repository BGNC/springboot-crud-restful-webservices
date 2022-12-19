package com.example.springbootcrudrestfulwebservices.service;

import com.example.springbootcrudrestfulwebservices.entity.User;
import com.example.springbootcrudrestfulwebservices.exception.ResourceNotFoundException;
import com.example.springbootcrudrestfulwebservices.exception.UserAlreadyAdded;
import com.example.springbootcrudrestfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("User not Found with id : " + id));
    }

    public User saveUser(User user) {
       // You need to check if there is a user belonging to such an id before, if there is the same user, do not add such an id.
        boolean isExistUser = userRepository.findById(user.getId()).isPresent();
        if(isExistUser) {
            throw new UserAlreadyAdded("This user is already exist!");
        }
        return userRepository.save(user);
    }

    public User updateUser(User user, long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not Found with id : " + id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public ResponseEntity<User> deleteUserById(long id) {
        boolean userExist = userRepository.existsById(id);
        if(!userExist) {
            throw new ResourceNotFoundException("There is no such an item you want to delete!");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
