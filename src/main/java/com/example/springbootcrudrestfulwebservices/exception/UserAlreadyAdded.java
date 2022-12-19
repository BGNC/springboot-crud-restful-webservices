package com.example.springbootcrudrestfulwebservices.exception;


public class UserAlreadyAdded extends RuntimeException{
    public UserAlreadyAdded(String message) {
        super(message);
    }
}
