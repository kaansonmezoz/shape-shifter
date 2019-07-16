package com.kaansonmezoz.shapeshifter;

import com.kaansonmezoz.shapeshifter.exceptions.ErrorType;
import com.kaansonmezoz.shapeshifter.exceptions.ExceptionMessageFactory;
import com.kaansonmezoz.shapeshifter.exceptions.ShapeShifterException;

import java.lang.reflect.Field;
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

    public void map(){ //TODO: Bunun bir de sadece targetClass verilmis halini yapalim
        sourceFields.forEach((sourceField) -> {
            String sourceFieldName = sourceField.getName();

            if(isSourceFieldExistInTarget(sourceFieldName)){


            }
        });

    }

    private void setTargetField(Field sourceField) throws IllegalAccessException {
        Field targetField = getTargetField(sourceField.getName());

        //TODO: Access modifierlara gore set etme islemi yapilacak
        //TODO: Once accessible yap ilgil field'ı sonra veriyi set et. Ardindan tekrar eski haline geri getir !

        int accessModifier = targetField.getModifiers();

        targetField.set(targetObject, sourceField.get(sourceObject));
    }

    //TODO: PrivateField'larda setter ve getter olmalı source'ta. Ama target object için böyle bir durum söz konuus değil
    //TODO: yani önce normal bir şekilde set edebiliyor muyuz ona bakmamız lazım edemiyorsak setter aramamız lazım.
    //TODO: setter yoksa ve herhangi bir şekilde set edemiyorsak değeri hata vermemiz gerekiyor

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
}
