package com.example.usernotifs.errors;

public class ServerRequestException extends RuntimeException {
    public ServerRequestException(String message) {
        super(message);
    }
}
