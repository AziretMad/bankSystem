package com.company.banksystem.exceptions;

public class NotFoundBankAccount extends Exception {
    private static final String message="NOT FOUND Bank Account. Please enter again";
    public NotFoundBankAccount(){
        super(message);
    }
}
