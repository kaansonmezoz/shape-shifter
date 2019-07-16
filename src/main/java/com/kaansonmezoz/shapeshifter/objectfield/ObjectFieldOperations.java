package com.kaansonmezoz.shapeshifter.objectfield;

import com.kaansonmezoz.shapeshifter.exceptions.ErrorType;
import com.kaansonmezoz.shapeshifter.exceptions.ShapeShifterException;

import java.lang.reflect.Field;

public class ObjectFieldOperations {
    public ObjectFieldOperations(){}

    private void setTargetField(){}

    private Field getTargetField(String fieldName, Class targetClass){
        Field targetField;

        try{
            targetField = getTargetPublicField(fieldName, targetClass);
        }catch(NoSuchFieldException ex){
            try {
                targetField = getTargetPrivateField(fieldName, targetClass);
            } catch (NoSuchFieldException e) {
                throw new ShapeShifterException(ErrorType.NoSuchFieldInTargetObject);
            }
        }
    }

    private Field getTargetPublicField(String fieldName, Class targetClass) throws NoSuchFieldException{
        return targetClass.getField(fieldName);
    }

    private Field getTargetPrivateField(String fieldName, Class targetClass) throws NoSuchFieldException{
        return targetClass.getDeclaredField(fieldName);
    }

}
