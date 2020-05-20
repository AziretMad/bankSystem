package com.company.banksystem.exceptions;

public class NotFoundTransaction extends Exception {
    private static final String message="Not found transaction. Please try again";
    public NotFoundTransaction(){
        super(message);
    }
}
