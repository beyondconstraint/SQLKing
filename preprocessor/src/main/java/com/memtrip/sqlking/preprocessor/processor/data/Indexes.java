package com.memtrip.sqlking.preprocessor.processor.data;

import java.util.List;

public class Indexes
    {
    private String tableName;
    private Index[] mIndexes;

    public String getTableName ()
        {
        return tableName;
        }

    public void setTableName (String tableName)
        {
        this.tableName = tableName;
        }

    public Index[] getIndexes ()
        {
        return mIndexes;
        }

    public void setIndexes (List<Index> newVal)
        {
        this.mIndexes = newVal.toArray(new Index[newVal.size()]);
        }
    }