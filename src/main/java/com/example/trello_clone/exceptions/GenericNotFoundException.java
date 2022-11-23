package com.example.trello_clone.exceptions;


public class GenericNotFoundException extends RuntimeException{
    public GenericNotFoundException(String message) {
        super(message);
    }

    public GenericNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
