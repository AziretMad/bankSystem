package com.company.banksystem.exceptions;

public class BankAccountIsNotActive extends Exception {
    private static final String message="Bank account isn't active. Please check again";
    public BankAccountIsNotActive(){
        super(message);
    }
}
