package com.kaansonmezoz.shapeshifter.exceptions;


public class ShapeShifterRuntimeException extends RuntimeException {
    public ShapeShifterRuntimeException(String message){
        super(message);
    }

    public ShapeShifterRuntimeException(String messageTemplate, String ... args){
        super(String.format(messageTemplate, args));
    }
}