package com.memtrip.sqlking.common;

public enum RIRule
    {
    SetNull     ("SET NULL"),
    SetDefault  ("SET DEFAULT"),
    Cascade     ("CASCADE"),
    Restrict    ("RESTRICT"),
    NotNull     ("NOT NULL"),
    NoAction    ("NO ACTION");

    private final String name;
    public final static RIRule values[] = values();

    RIRule (String name)
        {
        this.name = name;
        }

    public String getName()
        {
        return this.name;
        }
    };
