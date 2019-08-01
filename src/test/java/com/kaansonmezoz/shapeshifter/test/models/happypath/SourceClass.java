package com.kaansonmezoz.shapeshifter.test.models.happypath;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SourceClass {
    private String className;
    private String fieldName;
    private int classId;
    private double doubleValue;
    private boolean isTestClass;
    private Date currentDate;
    public Boolean isPublicField;
}
