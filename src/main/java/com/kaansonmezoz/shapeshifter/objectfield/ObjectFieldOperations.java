package com.kaansonmezoz.shapeshifter.objectfield;

import com.kaansonmezoz.shapeshifter.exceptions.ErrorType;
import com.kaansonmezoz.shapeshifter.exceptions.ExceptionMessageFactory;
import com.kaansonmezoz.shapeshifter.exceptions.ShapeShifterException;

import java.lang.reflect.Field;

public class ObjectFieldOperations {
    private Field[] targetPrivateFields;
    private Field[] targetPublicFields;
    private String className;

    public ObjectFieldOperations(Class targetClass){
        targetPrivateFields = targetClass.getDeclaredFields();
        targetPublicFields = targetClass.getFields();
        className = targetClass.getName();
    }

    private void setTargetField(){}

    private Field getTargetField(String fieldName) throws ShapeShifterException {
        Field targetField = getTargetPublicField(fieldName);

        if(targetField == null){
            return targetField;
        }
        else{
            targetField = getTargetPrivateField(fieldName);

            if(targetField != null){
                return targetField;
            }
            else{
                String errorMessage = new ExceptionMessageFactory().getExceptionMessageFor(
                        ErrorType.NoSuchFieldInTargetObject, fieldName, className
                );

                throw new ShapeShifterException(errorMessage);
            }
        }

    }

    private Field getTargetPublicField(String fieldName){
        for(Field field: targetPublicFields){
            if(field.getName().equals(fieldName)){
                return field;
            }
        }
        return null;
    }

    private Field getTargetPrivateField(String fieldName){
        for(Field field: targetPrivateFields){
            if(field.getName().equals(fieldName)){
                return field;
            }
        }
        return null;
    }

}
