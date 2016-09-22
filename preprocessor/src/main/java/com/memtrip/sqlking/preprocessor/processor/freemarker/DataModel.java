package com.memtrip.sqlking.preprocessor.processor.freemarker;

import com.memtrip.sqlking.preprocessor.processor.data.Data;
import com.memtrip.sqlking.preprocessor.processor.freemarker.method.*;

import java.util.HashMap;
import java.util.Map;

public class DataModel {

    private static final String TABLES = "tables";
    private static final String INDEXES = "indexes";
    private static final String FOREIGN_KEYS = "foreignKeys";
    private static final String CONSTRAINTS = "constraints";
    private static final String TRIGGERS = "triggers";

    public static Map<String, Object> create(Data data) {
        Map<String, Object> map = new HashMap<>();

        map.put(TABLES, data.getTables());
        map.put(INDEXES, data.getIndexes());
        map.put(FOREIGN_KEYS, data.getForeignKeys());
        map.put(CONSTRAINTS, data.getConstraints());
        map.put(TRIGGERS, data.getTriggers());

        map.putAll(GetCursorGetterMethod.getMethodMap());
        map.putAll(GetInsertValueMethod.getMethodMap());
        map.putAll(AssembleCreateTableMethod.getMethodMap());
        map.putAll(AssembleCreateIndexesMethod.getMethodMap());
        map.putAll(AssembleCreateForeignKeysMethod.getMethodMap());
        map.putAll(AssembleCreateConstraintsMethod.getMethodMap());
        map.putAll(AssembleCreateTriggersMethod.getMethodMap());
        map.putAll(FormatConstantMethod.getMethodMap());
        map.putAll(JoinSettersMethod.getMethodMap());
        map.putAll(JoinReferencesMethod.getMethodMap());

        return map;
    }
}
