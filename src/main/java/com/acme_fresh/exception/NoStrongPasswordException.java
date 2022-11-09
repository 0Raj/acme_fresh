package com.acme_fresh.exception;

public class NoStrongPasswordException extends RuntimeException {
    public NoStrongPasswordException(String msg) {
        super(msg);
    }
}
