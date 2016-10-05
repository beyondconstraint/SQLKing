package com.memtrip.sqlking.preprocessor;

//import com.memtrip.sqlking.common.*;
import com.memtrip.sqlking.common.Column;
import com.memtrip.sqlking.common.PrimaryKey;
import com.memtrip.sqlking.common.ForeignKey;
import com.memtrip.sqlking.common.Table;
import com.memtrip.sqlking.common.Index;
import com.memtrip.sqlking.common.IndexColumn;
import com.memtrip.sqlking.common.Constraint;
import com.memtrip.sqlking.common.NotNull;
import com.memtrip.sqlking.common.ConflictAction;

@Table(
        indexes = {
            @Index(
                indexName = "username",
                columns = {@IndexColumn(column = "username")}
            )
        },
        foreignKeys = {
            @ForeignKey(
                foreignTableName = "Log",
                localColumnNames = {"logId"},
                foreignColumnNames = {"id"}
            )
        },
        constraints = {
            @Constraint(
                constraintName = "check_username",
                expression = "length(username) > 5")
        }
)

public class User  {
    @Column(primaryKey = @PrimaryKey(active = true, auto_increment = false),
            notNull = @NotNull(onConflict = ConflictAction.ROLLBACK))
            String username;
    @Column long timestamp;
    @Column boolean isRegistered;
    @Column byte[] profilePicture;
    @Column double rating;
    @Column int count;
    @Column int logId;
    @Column Log log;

    public String getUsername() {
        return username;
    }
    public void setUsername(String newVal) {
        username = newVal;
    }

    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long newVal) {
        timestamp = newVal;
    }

    public boolean getIsRegistered() {
        return isRegistered;
    }
    public void setIsRegistered(boolean newVal) {
        isRegistered = newVal;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(byte[] newVal) {
        profilePicture = newVal;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public int getLogId() {
        return logId;
    }
    public void setLogId(int logId) {
        this.logId = logId;
    }

    public Log getLog() {
        return log;
    }
    public void setLog(Log log) {
        this.log = log;
    }
}