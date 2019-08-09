package com.kaansonmezoz.shapeshifter.test;

import com.kaansonmezoz.shapeshifter.ShapeShifter;
import com.kaansonmezoz.shapeshifter.exceptions.ShapeShifterException;
import com.kaansonmezoz.shapeshifter.test.models.happypath.SourceClass;
import com.kaansonmezoz.shapeshifter.test.models.happypath.TargetClass;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ShapeShifterTest {


    @Test
    public void map_happyPath() throws ShapeShifterException {
        // arrange
        SourceClass source = SourceClass.builder()
                .classId(1)
                .className("className")
                .currentDate(new Date())
                .doubleValue(1.0)
                .fieldName("fieldName")
                .isTestClass(true)
                .isPublicField(true)
                .build();

        TargetClass target = new TargetClass();

        // act
        ShapeShifter shapeShifter = new ShapeShifter(source, target);
        shapeShifter.map();

        // assert
        Assert.assertEquals(source.getClassId(), target.getClassId());
        Assert.assertEquals(source.getClassName(), target.getClassName());
        Assert.assertEquals(source.getCurrentDate(), target.getCurrentDate());
        Assert.assertEquals(source.getDoubleValue(), target.getDoubleValue(), 0.00001);
        Assert.assertEquals(source.getFieldName(), target.getFieldName());
        Assert.assertEquals(source.getIsPublicField(), target.getIsPublicField());
        Assert.assertEquals(source.isTestClass(), target.isTestClass());
    }
}
