package com.memtrip.sqlking.integration.models;

import com.memtrip.sqlking.common.Column;
import com.memtrip.sqlking.common.PrimaryKey;
import com.memtrip.sqlking.common.Table;

@Table
public class Log {
    @Column(@PrimaryKey(value = true))
            int  id;
    @Column long timestamp;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
