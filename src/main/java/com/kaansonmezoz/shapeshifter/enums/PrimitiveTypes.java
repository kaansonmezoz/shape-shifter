package com.kaansonmezoz.shapeshifter.enums;

public enum PrimitiveTypes {
    BOOLEAN("boolean"),
    BYTE("byte"),
    CHAR("char"),
    DOUBLE("double"),
    FLOAT("float"),
    INT("int"),
    LONG("long"),
    SHORT("short");


    private String type;

    PrimitiveTypes(String type) {
        this.type = type;
    }
}
