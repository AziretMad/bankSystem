package com.company.banksystem.exceptions;

public class NotFoundInterestRate extends Exception{
    private static final String message="Not found Interest Rate. Please enter again";

    public NotFoundInterestRate(){
        super(message);
    }
}
