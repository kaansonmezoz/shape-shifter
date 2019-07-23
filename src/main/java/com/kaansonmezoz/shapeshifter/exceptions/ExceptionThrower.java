package com.kaansonmezoz.shapeshifter.exceptions;

public class ExceptionThrower {
    public void throwExceptionForError(ErrorType errorType, String ... args) throws ShapeShifterException{
        if(errorType == ErrorType.NoSuchFieldInTargetObject){
            String message = "No such field " + args[0] + " found in target class " + args[1] + "\n" +
                             "Error message: " + args[2];

            throw new ShapeShifterException(message);
        }
        else if(errorType == ErrorType.IllegalAccessToPrivateField){
            throw new ShapeShifterRuntimeException(args[0]);
        }
        else if(errorType == ErrorType.CantCreateTargetObject){
            String message = String.format(
                    "Exception thrown during the initiation of %s.\n" +
                    "This might be cause when you don't have no arguments (default) constructor in the target class !\n" +
                    "Make sure you have no argument constructor !" +
                    "Exception message:\n %s",
                    args[0],
                    args[1]
            );

            throw new ShapeShifterRuntimeException(message);
        }

        throw new RuntimeException(String.format("Ooops something went wrong ! \n %s", args[0]));
    }
}
