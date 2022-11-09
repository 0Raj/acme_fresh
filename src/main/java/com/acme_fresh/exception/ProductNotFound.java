package com.acme_fresh.exception;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound(String msg) {
        super(msg);
    }
}
