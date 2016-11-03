package com.memtrip.sqlking.preprocessor.processor.data;

import java.util.List;

public class Data {
    private List<Table> mTables;
    private List<Index> mIndexes;
    private PrimaryKey mPrimaryKey;
    private List<ForeignKey> mForeignKeys;
    private List<Constraint> mConstraints;
    private List<Trigger> mTriggers;

    public List<Table> getTables() {
        return mTables;
    }
    public void setTables(List<Table> newVal) {
        mTables = newVal;
    }

    public List<Index> getIndexes() { return mIndexes; }
    public void setIndexes(List<Index> newVal) { mIndexes = newVal; }

    public PrimaryKey getPrimaryKey () { return mPrimaryKey; }
    public void setPrimaryKey (PrimaryKey newVal) { this.mPrimaryKey = newVal; }

    public List<ForeignKey> getForeignKeys () { return mForeignKeys; }
    public void setForeignKeys (List<ForeignKey> newVal) { this.mForeignKeys = newVal; }

    public List<Constraint> getConstraints () { return mConstraints; }
    public void setConstraints (List<Constraint> newVal) { this.mConstraints = mConstraints; }

    public List<Trigger> getTriggers () { return mTriggers; }
    public void setTriggers (List<Trigger> newVal) { this.mTriggers = newVal; }
}