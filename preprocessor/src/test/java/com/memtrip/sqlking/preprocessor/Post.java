package com.memtrip.sqlking.preprocessor;

import com.memtrip.sqlking.common.Column;
import com.memtrip.sqlking.common.Table;
import com.memtrip.sqlking.common.Index;
import com.memtrip.sqlking.common.IndexColumn;
import com.memtrip.sqlking.common.PrimaryKey;

@Table(
        primaryKey = @PrimaryKey(
                active = true,
                columns = {"id"},
                auto_increment = true
        ),
        indexes = {
              @Index(indexName = "timestamp",
                  columns = {
                        @IndexColumn(column = "timestamp")
                  }
              )
        }
)
public class Post {
    @Column int id;
    @Column String title;
    @Column byte[] blob;
    @Column long timestamp;
    @Column User user;
    @Column Data data;

    public int getId() {
        return id;
    }
    public void setId(int newVal) {
        id = newVal;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String newVal) {
        title = newVal;
    }

    public byte[] getBlob() {
        return blob;
    }
    public void setBlob(byte[] newVal) {
        blob = newVal;
    }

    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long newVal) {
        timestamp = newVal;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }
}