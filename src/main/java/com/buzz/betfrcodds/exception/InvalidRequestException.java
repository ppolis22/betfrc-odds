package com.buzz.betfrcodds.exception;

public class InvalidRequestException extends Exception {
    public InvalidRequestException() {}
    public InvalidRequestException(String message) {
        super(message);
    }
}
