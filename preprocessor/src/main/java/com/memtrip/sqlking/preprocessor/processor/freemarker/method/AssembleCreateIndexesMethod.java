package com.memtrip.sqlking.preprocessor.processor.freemarker.method;

import com.memtrip.sqlking.preprocessor.processor.data.IndexColumn;
import com.memtrip.sqlking.preprocessor.processor.data.Index;
import com.memtrip.sqlking.preprocessor.processor.data.Indexes;
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
    private String buildCreateIndexStatements(String tableName, Index[] indexes)
        {
        StringBuilder sb = new StringBuilder();

        for (Index index : indexes)
            {
            sb.append("CREATE INDEX " + index.getIndexName() + "ON " + tableName + "(");

            for (IndexColumn indexColumn : index.getColumns())
                {
                sb.append(indexColumn.getColumn())
                        .append(indexColumn.getSortOrder())
                        .append(",");
                }

            sb.deleteCharAt(sb.length() - 1);

            sb.append(");");

            }
        return "\"" + sb.toString() + "\";";
        }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        Object tableNameValue = arguments.get(0);
        Object indexListValue = arguments.get(1);

        Index[] indexes;

        if (indexListValue instanceof StringModel)
            {
            StringModel stringModel = (StringModel) indexListValue;
            indexes = (Index[])stringModel.getAdaptedObject(Indexes.class);
            }
        else
            {
            throw new IllegalStateException("The assembleCreateIndexStatements argument must be type of com.memtrip.sqlking.preprocessor.model.Indexes");
            }

        return buildCreateIndexStatements((String)tableNameValue, indexes);
    }
}