package com.kaansonmezoz.shapeshifter;

import com.kaansonmezoz.shapeshifter.objectfield.ObjectFieldOperations;

import java.lang.reflect.Field;

public class ShapeShifter {
    public ShapeShifter(){}

    public void map(Object sourceObject, Object targetObject){
        Field[] publicFields = sourceObject.getClass().getFields();
        Field[] privateFields = sourceObject.getClass().getDeclaredFields();

    }

    //TODO: PrivateField'larda setter ve getter olmalı source'ta. Ama target object için böyle bir durum söz konuus değil
    //TODO: yani önce normal bir şekilde set edebiliyor muyuz ona bakmamız lazım edemiyorsak setter aramamız lazım.
    //TODO: setter yoksa ve herhangi bir şekilde set edemiyorsak değeri hata vermemiz gerekiyor


    //TODO: field yoksa exception firlatacak sekilde ayarladik olayi ki en azindan ilgili field'in adini alarak
    //TODO: o field eger crucial ise bizler icin handle edilmesini saglayabiliriz
    private void mapPublicFields(Field[] sourcePublicFields, Object targetObject){
        ObjectFieldOperations objectFieldOperations = new ObjectFieldOperations();

        Class targetClass = targetObject.getClass();

        for(Field publicField: sourcePublicFields){

        }
    }


    private void mapPrivateFields(){}


}
