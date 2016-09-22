package com.memtrip.sqlking.preprocessor.processor.data;

import com.memtrip.sqlking.common.SortOrder;

public class IndexColumn {
    private String mColumn;
    private SortOrder mSortOrder;

    public String getColumn ()
        {
        return mColumn;
        }
    public void setColumn (String newVal)
        {
        this.mColumn = newVal;
        }

    public SortOrder getSortOrder ()
        {
        return mSortOrder;
        }
    public void setSortOrder (SortOrder newVal)
        {
        this.mSortOrder = newVal;
        }
}