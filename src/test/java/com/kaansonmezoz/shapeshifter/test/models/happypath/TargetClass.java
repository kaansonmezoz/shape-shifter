package com.kaansonmezoz.shapeshifter.test.models.happypath;

import lombok.Getter;

import java.util.Date;

@Getter
public class TargetClass {
    private String className;
    private String fieldName;
    private int classId;
    private double doubleValue;
    private boolean isTestClass;
    private Date currentDate;
    public Boolean isPublicField;

    public TargetClass(){}
}
