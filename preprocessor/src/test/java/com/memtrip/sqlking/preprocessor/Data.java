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
              @Index(indexName = "name",
                      columns = {@IndexColumn(column = "name")}
              )
        }
)
public class Data {
    @Column int id;
    @Column String name;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
