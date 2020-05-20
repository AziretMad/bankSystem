package com.company.banksystem.exceptions;

public class WrongPaymentAmount extends Exception{
    private static final String message = "The amount does not match the terms of the credit. " +
            "If you don't know how much you must pay, please, use paymentCalculator";
    public WrongPaymentAmount(){
        super(message);
    }
}
