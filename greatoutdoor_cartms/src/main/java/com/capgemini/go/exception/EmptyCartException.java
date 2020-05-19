package com.capgemini.go.exception;

public class EmptyCartException extends RuntimeException{
    public EmptyCartException(String msg){
        super(msg);
    }
}
