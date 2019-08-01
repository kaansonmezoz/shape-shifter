package com.kaansonmezoz.shapeshifter;

import com.kaansonmezoz.shapeshifter.enums.PrimitiveTypes;
import com.kaansonmezoz.shapeshifter.exceptions.ErrorType;
import com.kaansonmezoz.shapeshifter.exceptions.ShapeShifterException;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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

    public ShapeShifter(Object sourceObject){
        this.sourceObject = sourceObject;
        sourceClass = sourceObject.getClass();
        sourceFields = getAllFieldsAsList(sourceClass);
    }

    public ShapeShifter(Object sourceObject, Object targetObject){
        this(sourceObject);

        this.targetObject = targetObject;
        targetClass = targetObject.getClass();
        targetFields = getAllFieldsAsHashMap(targetClass);
    }

    public void map() throws ShapeShifterException { //TODO: Bunun bir de sadece targetClass verilmis halini yapalim
        String sourceFieldName;

        try{
            for(Field sourceField: sourceFields){
                sourceFieldName = sourceField.getName();

                if(isSourceFieldExistInTarget(sourceFieldName)){
                    setTargetField(sourceField);
                }
                else{
                    // Belli bir error tipine gore exception firlatmamizi sagliyor
                    // ne tarz bir yol izlemek istedigimize bagli birazcik da cozum aslında
                    // amac abstarct bir yapı saglamak bunun icin olabilecek alternatif yollari da dusunelim bence

                    //TODO: Belki de field bulamazsa bos gecmeliyiz hata da fırlatmak yerine !
                    //TODO: Daha iyi bir sonuc verebilir bize 

                    ErrorType.NO_SUCH_FIELD_IN_TARGET_OBJECT.throwException(
                            sourceFieldName,
                            targetClass.getCanonicalName()
                    );
                }
            }
        }catch(IllegalAccessException ex){
            ErrorType.ILLEGAL_ACCESS_TO_PRIVATE_FIELD.throwRuntimeException(
                    ex.getMessage()
            );
        }
    }

    public Object map(Class targetClass){
        return null;
    }

    private void setTargetField(Field sourceField) throws IllegalAccessException {
        Field targetField = getTargetField(sourceField.getName());

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
        List<Field> fields = Arrays.asList(objectClass.getFields());
        fields.addAll(Arrays.asList(objectClass.getDeclaredFields()));

        return fields;
    }

    private HashMap<String, Field> getAllFieldsAsHashMap(Class objectClass){
        HashMap<String, Field> fieldsHashMap = new HashMap<>();
        List<Field> fields = getAllFieldsAsList(objectClass);

        fields.forEach((field) -> {
            String fieldName = field.getName();
            fieldsHashMap.put(fieldName, field);
        });

        return fieldsHashMap;
    }

    private void callSetterMethodFor(Field sourceField, Field targetField) throws IllegalAccessException{
        String fieldType = sourceField.getType().getTypeName();

        if(fieldType.equals(PrimitiveTypes.BOOLEAN.toString())){
            targetField.setBoolean(targetObject, sourceField.getBoolean(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.BYTE.toString())){
            targetField.setByte(targetObject, sourceField.getByte(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.CHAR.toString())){
            targetField.setChar(targetObject, sourceField.getChar(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.DOUBLE.toString())){
            targetField.setDouble(targetObject, sourceField.getDouble(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.FLOAT.toString())){
            targetField.setFloat(targetObject, sourceField.getFloat(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.INT.toString())){
            targetField.setInt(targetObject, sourceField.getInt(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.LONG.toString())){
            targetField.setLong(targetObject, sourceField.getInt(sourceObject));
        }
        else if(fieldType.equals(PrimitiveTypes.SHORT.toString())){
            targetField.setShort(targetObject, sourceField.getShort(sourceObject));
        }
        else{
            targetField.set(targetObject, sourceField.get(sourceObject));
        }
    }
}
