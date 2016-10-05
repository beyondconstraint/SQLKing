package com.memtrip.sqlking.preprocessor.processor.data.validator;

import com.memtrip.sqlking.preprocessor.processor.Validator;
import com.memtrip.sqlking.preprocessor.processor.ValidatorException;
import com.memtrip.sqlking.preprocessor.processor.data.Column;
import com.memtrip.sqlking.preprocessor.processor.data.Data;
import com.memtrip.sqlking.preprocessor.processor.data.Table;
import com.memtrip.sqlking.preprocessor.processor.data.PrimaryKey;

import java.util.List;

public class PrimaryKeyMustBeUnique implements Validator {

    private Data data;

    public PrimaryKeyMustBeUnique(Data data) {
        this.data = data;
    }

    private boolean primaryKeyIsUniqueInColumns(Table table) {
        int occurrences = 0;

//        PrimaryKey primaryKey = table.getPrimaryKey();
//
//        if (primaryKey != null && primaryKey.getColumns().length > 0)
//            {
//            occurrences++;
//            }

//        if (table.getColumns() != null) {
//            for (Column column : table.getColumns()) {
//
//                PrimaryKey primaryKey = column.getPrimaryKey();
//
//                if (primaryKey != null) {
////                if (primaryKey.getColumns().length > 0) {
//                    occurrences++;
//                }
//            }
//        }

        return occurrences > 1;
    }

    @Override
    public void validate() throws ValidatorException {
        for (Table table : data.getTables()) {
            if (primaryKeyIsUniqueInColumns(table)) {
                throw new ValidatorException(
                        table.getElement(),
                        "[Duplicate primary_key's found in @Table: `" + table.getName()
                                + ", only specify one primary_key Column per table]"
                );
            }
        }
    }
}