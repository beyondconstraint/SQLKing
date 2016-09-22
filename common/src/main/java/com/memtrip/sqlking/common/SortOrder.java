/**
 * Author: A.Velcich
 */

package com.memtrip.sqlking.common;

public enum SortOrder
    {
    ASC    ("ASC"),
    DESC   ("DESC");

    private final String text;

    SortOrder (String newVal) { text = newVal; }
    public final static SortOrder values[] = values();
    }