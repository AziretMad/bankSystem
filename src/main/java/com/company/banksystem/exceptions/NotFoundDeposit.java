package com.company.banksystem.exceptions;

public class NotFoundDeposit extends Exception {
    private final static String message="Not Found Deposit. Please enter again.";

    public NotFoundDeposit(){
        super(message);
    }
}
