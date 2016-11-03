package com.memtrip.sqlking.preprocessor.processor.data;

import java.util.List;


public class Column {
    private String  mName;
    private String  mClassName;
    private String  mType;
    private PrimaryKey mPrimaryKey;
    private ForeignKey mForeignKey;
    private boolean mIsIndex;
    private NotNull mNotNull;
    private boolean mUnique;
    private String  mDefaultValue;
    private List<Constraint>  mConstraints;

    public String getName() {
        return mName;
    }
    public void setName(String newVal) {
        mName = newVal;
    }

    public String getClassName() {
        return mClassName;
    }
    public void setClassName(String newVal) {
        mClassName = newVal;
    }

    public String getType() {
        return mType;
    }
    public void setType(String newVal) {
        mType = newVal;
    }

    public PrimaryKey getPrimaryKey() { return mPrimaryKey; }
    public void setPrimaryKey(PrimaryKey newVal) { mPrimaryKey = newVal; }

    public boolean isIndex() {
        return mIsIndex;
    }
    public void setIsIndex(boolean newVal) {
        mIsIndex = newVal;
    }

    public NotNull getNotNull ()
        {
        return mNotNull;
        }
    public void setNotNull (NotNull newVal)
        {
        this.mNotNull = newVal;
        }

    public boolean isUnique () { return mUnique; }
    public void setUnique (boolean mUnique) { this.mUnique = mUnique; }

    public String getDefaultValue ()
        {
        return mDefaultValue;
        }
    public void setDefaultValue (String newVal)
        {
        this.mDefaultValue = newVal;
        }

    public ForeignKey getForeignKey () { return mForeignKey; }
    public void setForeignKey (ForeignKey newVal) { this.mForeignKey = newVal; }

    public List<Constraint> getConstraints () { return mConstraints; }
    public void setConstraints (List<Constraint> newVal) { this.mConstraints = newVal; }

    public Table getRootTable (List<Table> tables) {
        if (isJoinable(tables)) {
            for (Table table : tables) {
                if (table.getType().equals(mType)) {
                    return table;
                }
            }
        }

        throw new IllegalStateException("Only joinable columns can call getRootTable");
    }

    public boolean isJoinable(List<Table> tables) {
        for (Table table : tables) {
            if (table.getName().toLowerCase().equals(mClassName.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
}