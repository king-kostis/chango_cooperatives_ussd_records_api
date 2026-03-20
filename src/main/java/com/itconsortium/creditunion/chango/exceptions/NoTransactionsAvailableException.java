package com.itconsortium.creditunion.chango.exceptions;

public class NoTransactionsAvailableException extends RuntimeException {
    public NoTransactionsAvailableException(String message) {
        super(message);
    }
}
