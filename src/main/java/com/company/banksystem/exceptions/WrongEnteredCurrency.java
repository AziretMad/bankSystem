package com.company.banksystem.exceptions;

public class WrongEnteredCurrency extends Exception {
    private static final String message="Incorrect entered currency. Please enter again";
    public WrongEnteredCurrency(){
        super(message);
    }
}
