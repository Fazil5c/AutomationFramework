package com.boa.util;

public class AutomationException extends RuntimeException {
    public AutomationException(String message){
        super(message);
        System.err.println(message);
    }
}
