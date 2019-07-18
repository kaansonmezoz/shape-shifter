package com.kaansonmezoz.shapeshifter.exceptions;

public class ExceptionMessageFactory {
    public String getExceptionMessageFor(ErrorType errorType, String ... args){
        if(errorType == ErrorType.NoSuchFieldInTargetObject){
            return "No such field " + args[0] + " found in target class " + args[1];
        }
        return null;
    }
}
