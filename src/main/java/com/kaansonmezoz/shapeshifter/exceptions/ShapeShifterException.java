package com.kaansonmezoz.shapeshifter.exceptions;

public class ShapeShifterException extends Exception{
    public ShapeShifterException(String messageTemplate, String ... args){
        super(String.format(messageTemplate, args));
    }
}
