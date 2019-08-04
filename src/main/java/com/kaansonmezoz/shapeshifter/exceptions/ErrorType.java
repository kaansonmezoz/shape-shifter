package com.kaansonmezoz.shapeshifter.exceptions;

public enum ErrorType {
    NO_SUCH_FIELD_IN_TARGET_OBJECT(
        "No such field %s found in target class %s\n" +
        "Error message: %s"
    ),

    ILLEGAL_ACCESS_TO_PRIVATE_FIELD(
        "Illegal access to private field %s.\n" +
         "Exception message:\n %s"
    ),

    CANT_CREATE_TARGET_OBJECT(
        "Exception thrown during the initiation of %s.\n" +
        "This might be cause when you don't have no arguments (default) constructor in the target class !\n" +
        "Make sure you have no argument constructor !" +
        "Exception message:\n %s"
    );

    private String errorMessageTemplate;

    ErrorType(String errorMessageTemplate){
        this.errorMessageTemplate = errorMessageTemplate;
    }

    public void throwRuntimeException(String ... exceptionMessageArguments){
        throw new ShapeShifterRuntimeException(
                errorMessageTemplate,
                exceptionMessageArguments
        );
    }

    public void throwException(String ... exceptionMessageArguments) throws ShapeShifterException {
        throw new ShapeShifterException(
                errorMessageTemplate,
                exceptionMessageArguments
        );
    }
}
