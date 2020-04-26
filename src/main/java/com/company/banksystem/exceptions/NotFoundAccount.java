package com.company.banksystem.exceptions;

public class NotFoundAccount extends Exception {
    private static final String message="Not Found Account. Please enter again.";

    public NotFoundAccount (){
        super(message);
    }
}
