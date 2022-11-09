package com.acme_fresh.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String user_not_found) {
        super(user_not_found);
    }
}
