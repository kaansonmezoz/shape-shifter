package com.kaansonmezoz.shapeshifter.exceptions;

public class ExceptionThrower {
    private volatile static ExceptionThrower exceptionThrower;

    private ExceptionThrower(){}

    public static ExceptionThrower getInstance(){
        if(exceptionThrower == null){
            synchronized (ExceptionThrower.class){
                if(exceptionThrower == null){
                    exceptionThrower = new ExceptionThrower();
                }
            }
        }

        return exceptionThrower;
    }

    // TODO: Hala daha iyi bir çözüm lazım !

    public void throwException(String exceptionMessage){
        throw new ShapeShifterRuntimeException(exceptionMessage);
    }

    protected void throwException(String messageTemplate, String targetClassName, String exceptionMessage){
        throw new ShapeShifterRuntimeException(messageTemplate, targetClassName, exceptionMessage);
    }

    public void throwException(String messageTemplate, String fieldName, String className, String exceptionMessage){
        throw new ShapeShifterRuntimeException(messageTemplate, fieldName, className, exceptionMessage);
    }
}
