package com.company.banksystem.exceptions;

public class NotFoundClient extends Exception {
    private final static String message="Not Found Client. Please enter again.";

    public NotFoundClient(){
        super(message);
    }
}
