package com.memtrip.sqlking.preprocessor.processor.data;

/**
 * Author: A.Velcich
 */

import com.memtrip.sqlking.common.ConflictAction;

public class NotNull
    {
    private ConflictAction  mOnConflict = ConflictAction.NONE;

    public ConflictAction getOnConflict () { return mOnConflict; }
    public void setOnConflict (ConflictAction newVal) { this.mOnConflict = newVal; }
    }