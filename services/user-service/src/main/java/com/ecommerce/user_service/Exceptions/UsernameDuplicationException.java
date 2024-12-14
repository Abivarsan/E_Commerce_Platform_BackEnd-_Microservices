package com.ecommerce.user_service.Exceptions;

public class UsernameDuplicationException extends RuntimeException {
    public UsernameDuplicationException(String message) {
        super(message);
    }
}
