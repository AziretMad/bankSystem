package com.company.banksystem.exceptions;

public class WrongConfirmationCode extends Exception {
    private static final String message="Wrong confirmation code";
    public WrongConfirmationCode() {
        super(message);
    }
}
