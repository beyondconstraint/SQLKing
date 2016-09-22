package com.memtrip.sqlking.preprocessor;

import com.memtrip.sqlking.common.Column;
import com.memtrip.sqlking.common.Table;
import com.memtrip.sqlking.common.Index;
import com.memtrip.sqlking.common.IndexColumn;
import com.memtrip.sqlking.common.ForeignKey;

@Table(
        indexes = {
              @Index(indexName = "timestamp",
                      columns = {
                            @IndexColumn(column = "timestamp")
                      }
              )
        }
)
public class Log {
    @Column(primary_key = true) int id;
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