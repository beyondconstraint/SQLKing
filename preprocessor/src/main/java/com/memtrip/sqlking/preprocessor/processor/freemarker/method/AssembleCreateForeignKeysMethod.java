package com.memtrip.sqlking.preprocessor.processor.freemarker.method;

import com.memtrip.sqlking.preprocessor.processor.data.IndexColumn;
import com.memtrip.sqlking.preprocessor.processor.data.Index;
import com.memtrip.sqlking.preprocessor.processor.data.ForeignKey;
import com.memtrip.sqlking.preprocessor.processor.data.Table;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssembleCreateForeignKeysMethod implements TemplateMethodModelEx
    {
    private static final String ASSEMBLE_CREATE_FOREIGN_KEYS = "assembleCreateCreateForeignKeys";

    public static Map<String, Object> getMethodMap ()
        {
        Map<String, Object> map = new HashMap<>();
        map.put(ASSEMBLE_CREATE_FOREIGN_KEYS, new AssembleCreateForeignKeysMethod());
        return map;
        }

    /**
     * Build a list of create ForeignKey statement baseds on the provided tableName
     *
     * @param  tableName
     * @param foreignKeys The list of Foreign Keys that will have create statements created for them
     * @return A SQL statement that will create a table
     */
    private String buildCreateForeignKeyStatements (Table table)
        {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbDebug = new StringBuilder();

        List<ForeignKey> foreignKeyList = table.getForeignKeys();

        for (ForeignKey foreignKey : foreignKeyList)
            {
            sbDebug.append("On " + table.getName() + ", Foreign Key references table ")
                   .append(foreignKey.getForeignTableName())
                   .append(" local columns (")
                   .append(foreignKey.getLocalColumnNames().toString().replaceAll("[\\[\\]]", ""))
                   .append(") foreign columns (")
                   .append(foreignKey.getForeignColumnNames().toString().replaceAll("[\\[\\]]", ""))
                   .append(");");

            System.out.println("\"" + sbDebug.toString() + "\";");
            }

        for (ForeignKey foreignKey : foreignKeyList)
            {
            sb.append("CREATE FOREIGN KEY(")
                    .append(foreignKey.getLocalColumnNames().toString().replaceAll("[\\[\\]]", ""))
                    .append(") REFERENCES ")
                    .append(foreignKey.getForeignTableName())
                    .append("(")
                    .append(foreignKey.getForeignColumnNames().toString().replaceAll("[\\[\\]]", ""))
                    .append(");");
            }

        return "\"" + sb.toString() + "\";";
        }


    @Override
    public Object exec (List arguments) throws TemplateModelException
        {
        Object tableValue = arguments.get(0);

        Table table;

        if (tableValue instanceof StringModel)
            {
            StringModel stringModel = (StringModel)tableValue;
            table = (Table)stringModel.getAdaptedObject(Table.class);
            }
        else
            {
            throw new IllegalStateException("The assembleCreateForeignKeyStatements Table argument must be type of " +
                                            "com.memtrip.sqlking.preprocessor.processor.data.Table");
            }

        return buildCreateForeignKeyStatements(table);
        }
    }