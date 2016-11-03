package com.memtrip.sqlking.preprocessor.processor.data;

import com.memtrip.sqlking.common.ConflictAction;

public class PrimaryKey
    {
    private boolean mActive;
    private boolean mAutoNumber;
    private String[] mColumns;
    private ConflictAction mOnConflict;

    public boolean isActive () { return mActive; }
    public void setIsActive (boolean newVal) { this.mActive = newVal; }

    public boolean isAutoNumber () { return mAutoNumber; }
    public void setAutoNumber (boolean newVal) { this.mAutoNumber = newVal; }

    public String[] getColumns () { return mColumns; }
    public void setColumns (String[] newVal) { this.mColumns = newVal; }

    public ConflictAction getOnConflict () { return mOnConflict; }
    public void setOnConflict (ConflictAction newVal) { mOnConflict = newVal; }
    }