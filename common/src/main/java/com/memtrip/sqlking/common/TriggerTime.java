/**
 * Author: A.Velcich
 */

package com.memtrip.sqlking.common;

/**
 * Author: A.Velcich
 */

public enum TriggerTime
    {
    BEFORE      ("BEFORE"),
    AFTER       ("AFTER"),
    INSTEAD_OF  ("INSTEAD OF");

    private final String text;

    TriggerTime (String newVal) { text = newVal; }
    public final static TriggerTime values[] = values();
    public String toString() { return text; }
    }