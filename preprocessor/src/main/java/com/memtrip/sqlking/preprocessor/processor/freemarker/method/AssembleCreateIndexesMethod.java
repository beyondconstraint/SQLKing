package com.memtrip.sqlking.preprocessor.processor.freemarker.method;

import com.memtrip.sqlking.preprocessor.processor.data.IndexColumn;
import com.memtrip.sqlking.preprocessor.processor.data.Index;
import com.memtrip.sqlking.preprocessor.processor.data.Table;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssembleCreateIndexesMethod implements TemplateMethodModelEx {

    private static final String ASSEMBLE_CREATE_INDEXES = "assembleCreateIndexes";

    public static Map<String, Object> getMethodMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(ASSEMBLE_CREATE_INDEXES, new AssembleCreateIndexesMethod());
        return map;
    }

    private AssembleCreateIndexesMethod() {
    }

    /**
     * Build a list of create Index statement baseds on the provided tableName
     * @param	indexes	The list of indexes that will have create statements created for them
     * @return	A SQL statement that will create a table
     */
    private String buildCreateIndexStatements(Table table)
        {
        StringBuilder sb = new StringBuilder();

        List<Index> indexes = table.getIndexes();

        for (Index index : indexes)
            {
            sb.append("CREATE INDEX " + index.getIndexName() + " ON " + index.getTableName() + " (");

            for (IndexColumn indexColumn : index.getColumns())
                {
                sb.append(indexColumn.getColumn())
                        .append(indexColumn.getSortOrder())
                        .append(",");
                }

            sb.deleteCharAt(sb.length() - 1);

            sb.append(");");

            }
        return "\"" + sb.toString() + "\"";
        }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        Object tableValue = arguments.get(0);

        Table table;
        if (tableValue instanceof StringModel)
            {
            StringModel stringModel = (StringModel) tableValue;
            table = (Table)stringModel.getAdaptedObject(Table.class);
            }
        else
            {
            throw new IllegalStateException("The assembleCreateIndexStatements argument must be type of com.memtrip.sqlking.preprocessor.model.Table");
            }

        return buildCreateIndexStatements(table);
    }
}