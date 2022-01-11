package com.batsandrey.sweater.exception;

public class NoEntityException extends RuntimeException {
    public NoEntityException(Long managerId) {
        super(String.format("Entity with this id %d not found", managerId));
    }
}
