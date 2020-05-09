package com.company.banksystem.exceptions;

public class NotFoundStatement extends Exception {
    private static final String message="Not Found Statement. Please enter again.";

    public NotFoundStatement (){
        super(message);
    }
}
