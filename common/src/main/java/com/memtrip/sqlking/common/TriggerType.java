/**
 * Author: A.Velcich
 */

package com.memtrip.sqlking.common;

/**
 * Author: A.Velcich
 */

public enum TriggerType
    {
    DELETE      ("DELETE"),
    INSERT      ("INSERT"),
    UPDATE      ("UPDATE");

    private final String text;

    TriggerType (String newVal) { text = newVal; }
    public final static TriggerType values[] = values();
    public String toString() { return text; }
    }