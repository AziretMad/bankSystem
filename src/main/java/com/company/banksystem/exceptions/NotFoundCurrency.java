package com.company.banksystem.exceptions;

public class NotFoundCurrency extends Exception {
    private static final String message="Not found Currency. Please try again";
    public NotFoundCurrency() {
        super(message);
    }
}
