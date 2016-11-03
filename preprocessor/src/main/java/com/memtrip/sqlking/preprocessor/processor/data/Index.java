package com.memtrip.sqlking.preprocessor.processor.data;

import java.util.List;

public class Index {
    private String              mTableName;
    private String              mIndexName;
    private boolean             mIsUnique;
    private List<IndexColumn>   mColumns;

    public String getTableName () { return mTableName; }
    public void setTableName (String newVal) { this.mTableName = newVal; }

    public String getIndexName ()
            {
            return mIndexName;
            }
    public void setIndexName (String newVal)
        {
        this.mIndexName = newVal;
        }

    public List<IndexColumn> getColumns ()
            {
            return mColumns;
            }
    public void setColumns (List<IndexColumn> newVal)
        {
        this.mColumns = newVal;
        }

    public boolean isUnique ()
        {
        return mIsUnique;
        }
    public void setUnique (boolean newVal)
        {
        this.mIsUnique = newVal;
        }
}