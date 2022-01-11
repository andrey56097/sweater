package com.batsandrey.sweater.exception;

public class TodoWithoutManagerException extends RuntimeException {
    public TodoWithoutManagerException(String message) {
        super(message);
    }
}
