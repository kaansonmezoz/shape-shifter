package com.kaansonmezoz.shapeshifter;

import com.kaansonmezoz.shapeshifter.enums.PrimitiveTypes;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ShapeShifter {
    private Object sourceObject;
    private Object targetObject;

    private Class sourceClass;
    private Class targetClass;

    private List<Field> sourceFields;
    private HashMap<String, Field> targetFields;

    public ShapeShifter(Object sourceObject, Object targetObject){
        this.sourceObject = sourceObject;
        this.targetObject = targetObject;

        sourceClass = sourceObject.getClass();
        targetClass = targetObject.getClass();

        sourceFields = getAllFieldsAsList(sourceClass);
        targetFields = getAllFieldsAsHashMap(targetClass);
    }

    public void map() throws IllegalAccessException{ //TODO: Bunun bir de sadece targetClass verilmis halini yapalim
        String sourceFieldName;

        for(Field sourceField: sourceFields){
            sourceFieldName = sourceField.getName();

            if(isSourceFieldExistInTarget(sourceFieldName)){
                setTargetField(sourceField);
            }
        }
    }

    private void setTargetField(Field sourceField) throws IllegalAccessException {
        Field targetField = getTargetField(sourceField.getName());

        /*
         */

        int accessModifier = targetField.getModifiers();


        if(Modifier.isPublic(accessModifier)){
            callSetterMethodFor(sourceField, targetField);
        }
        else{
            targetField.setAccessible(true);
            callSetterMethodFor(sourceField, targetField);
            targetField.setAccessible(false);
        }
    }

    private boolean isSourceFieldExistInTarget(String sourceFieldName){
        return targetFields.containsKey(sourceFieldName);
    }

    private Field getTargetField(String fieldName){
        return targetFields.get(fieldName);
    }

    private List<Field> getAllFieldsAsList(Class objectClass){
        ArrayList<Field> fields = new ArrayList<Field>(Arrays.asList(objectClass.getFields()));
        fields.addAll(Arrays.asList(objectClass.getDeclaredFields()));

        return fields;
    }

    private HashMap<String, Field> getAllFieldsAsHashMap(Class objectClass){
        HashMap<String, Field> fieldsHashMap = new HashMap<String, Field>();
        List<Field> fields = getAllFieldsAsList(objectClass);

        fields.forEach((field) -> {
            String fieldName = field.getName();
            fieldsHashMap.put(fieldName, field);
        });

        return fieldsHashMap;
    }

    private void callSetterMethodFor(Field sourceField, Field targetField) throws IllegalAccessException{
        String fieldType = sourceField.getType().getTypeName();

        if(fieldType.equals(PrimitiveTypes.BOOLEAN)){
            targetField.setBoolean(targetObject, sourceField.getBoolean(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.BYTE)){
            targetField.setByte(targetObject, sourceField.getByte(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.CHAR)){
            targetField.setChar(targetObject, sourceField.getChar(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.DOUBLE)){
            targetField.setDouble(targetObject, sourceField.getDouble(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.FLOAT)){
            targetField.setFloat(targetObject, sourceField.getFloat(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.INT)){
            targetField.setInt(targetObject, sourceField.getInt(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.LONG)){
            targetField.setInt(targetObject, sourceField.getInt(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.SHORT)){
            targetField.setShort(targetObject, sourceField.getShort(sourceObject));
        }
        else{
            targetField.set(targetObject, sourceField.get(sourceObject));
        }
    }

}
