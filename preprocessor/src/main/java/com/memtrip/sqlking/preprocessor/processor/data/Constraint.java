package com.memtrip.sqlking.preprocessor.processor.data;

import java.util.List;
import com.memtrip.sqlking.preprocessor.processor.utils.StringUtils;
import com.memtrip.sqlking.common.ConflictAction;

public class Constraint
    {
    private String          mConstraintName;
    private String          mConstraintExpression;
    private ConflictAction  mOnConflict = ConflictAction.NA;

    public String getConstraintName ()
        {
        return mConstraintName;
        }
    public void setConstraintName (String newVal)
        {
        this.mConstraintName = newVal;
        }

    public String getConstraintExpression ()
        {
        return mConstraintExpression;
        }
    public void setConstraintExpression (String newVal)
        {
        this.mConstraintExpression = newVal;
        }

    public ConflictAction getOnConflict () { return mOnConflict; }
    public void setOnConflict (ConflictAction newVal) { this.mOnConflict = newVal; }
    }