package com.company.banksystem.exceptions;

public class WrongKeyWordException extends Exception {
    private static final String message = "Wrong code word";

    public WrongKeyWordException() {
        super(message);
    }
}
