package com.memtrip.sqlking.preprocessor.processor.data;

/**
 * Author: A.Velcich
 */

import java.util.List;
import com.memtrip.sqlking.common.RIRule;

public class ForeignKey
    {
    private String       mForeignTableName;
    private String       mForeignTableAliasName;
    private List<String> mLocalColumnNames;
    private List<String> mForeignColumnNames;
    private RIRule       mRIUpdateRule;
    private RIRule       mRIDeleteRule;

    public String getForeignTableName() {
        return mForeignTableName;
    }
    public void setForeignTableName(String newVal) {
    mForeignTableName = newVal;
    }

    public String getForeignTableAliasName () { return mForeignTableAliasName; }
    public void setForeignTableAliasName (String newVal) { this.mForeignTableAliasName = newVal; }

    public List<String> getLocalColumnNames () { return mLocalColumnNames; }
    public void setLocalColumnNames(List<String> newVal) { mLocalColumnNames = newVal; }

    public List<String> getForeignColumnNames() { return mForeignColumnNames; }
    public void setForeignColumnNames(List<String> newVals) {
        mForeignColumnNames = newVals;
    }

    public RIRule getRIUpdateRule ()
        {
        return mRIUpdateRule;
        }
    public void setRIUpdateRule (RIRule newVal)
        {
        this.mRIUpdateRule = newVal;
    }

    public RIRule getRIDeleteRule ()
        {
        return mRIDeleteRule;
    }
    public void setRIDeleteRule (RIRule newVal)
        {
        this.mRIDeleteRule = newVal;
    }
}