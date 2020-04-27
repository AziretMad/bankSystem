package com.company.banksystem.exceptions;

public class NotFoundCredit extends Exception {
    private final static String message="Not Found Credit. Please enter again.";

    public NotFoundCredit(){
        super(message);
    }
}
