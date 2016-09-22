/**
 * Author: A.Velcich
 */

package com.memtrip.sqlking.preprocessor.processor.data;

/**
 * Author: A.Velcich
 */

import java.util.List;
import com.memtrip.sqlking.preprocessor.processor.utils.StringUtils;
import com.memtrip.sqlking.common.TriggerTime;
import com.memtrip.sqlking.common.TriggerType;

public class Trigger
    {
    private String       mTriggerName;
    private TriggerType  mTriggerType;
    private TriggerTime  mTriggerTime;
    private List<String> mOnUpdateOfColumnNames;
    private boolean      mForEachRow;
    private String       mWhenExpression;
    private String       mStatement;

    public String getTriggerName ()
        {
        return mTriggerName;
        }
    public void setTriggerName (String newVal)
        {
        this.mTriggerName = newVal;
        }

    public TriggerType getTriggerType () { return mTriggerType; }
    public void setTriggerType (TriggerType newVal) { this.mTriggerType = newVal; }

    public TriggerTime getTriggerTime () { return mTriggerTime; }
    public void setTriggerTime (TriggerTime mTriggerTime) { this.mTriggerTime = mTriggerTime; }

    public List<String> getOnUpdateOfColumnNames () { return mOnUpdateOfColumnNames; }
    public void setOnUpdateOfColumnNames (List<String> mOnUpdateOfColumnNames) {
        this.mOnUpdateOfColumnNames = mOnUpdateOfColumnNames;
        }

    public boolean isForEachRow () { return mForEachRow; }
    public void setForEachRow (boolean mForEachRow) { this.mForEachRow = mForEachRow; }

    public String getWhenExpression () { return mWhenExpression; }
    public void setWhenExpression (String newVal) { this.mWhenExpression = newVal; }

    public String getStatement () { return mStatement; }
    public void setStatement (String mStatement) { this.mStatement = mStatement; }
    }