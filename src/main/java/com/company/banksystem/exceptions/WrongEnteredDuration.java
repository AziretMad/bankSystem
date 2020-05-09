package com.company.banksystem.exceptions;

public class WrongEnteredDuration extends Exception {
    private static final String message="Incorrect entered duration";
    public WrongEnteredDuration(){
        super(message);
    }
}
