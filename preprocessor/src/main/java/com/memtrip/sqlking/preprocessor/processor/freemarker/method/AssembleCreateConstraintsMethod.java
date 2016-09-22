package com.memtrip.sqlking.preprocessor.processor.freemarker.method;

import com.memtrip.sqlking.common.ConflictAction;

import com.memtrip.sqlking.preprocessor.processor.data.Constraint;
import com.memtrip.sqlking.preprocessor.processor.data.Table;

import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssembleCreateConstraintsMethod implements TemplateMethodModelEx
    {
    private static final String ASSEMBLE_CREATE_CONSTRAINTS = "assembleCreateConstraints";

    public static Map<String, Object> getMethodMap ()
        {
        Map<String, Object> map = new HashMap<>();
        map.put(ASSEMBLE_CREATE_CONSTRAINTS, new AssembleCreateConstraintsMethod());
        return map;
        }

    /**
     * Build a list of create Constraint statement baseds on the provided table
     *
     * @param  table
     * @param constraints The list of Constraints that will have create statements created for them
     * @return A SQL statement that will create the constraints during CREATE TABLE
     */
    private String buildCreateConstraintStatements (Table table)
        {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbDebug = new StringBuilder();

        List<Constraint> constraintList = table.getConstraints();

        for (Constraint constraint : constraintList)
            {
            sb.append(" CONSTRAINT ")
                    .append(constraint.getConstraintName())
                    .append(" ")
                    .append(constraint.getConstraintExpression())
                    .append(" ON CONFLICT ")
                    .append(constraint.getOnConflict().toString())
                    .append(",");
            }

        if (sb.length() > 0) { sb.deleteCharAt(sb.length() - 1); }

        return "\"" + sb.toString() + "\"";
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
            throw new IllegalStateException("The assembleCreateConstraintStatements Table argument must be type of " +
                                            "com.memtrip.sqlking.preprocessor.processor.data.Table");
            }

        return buildCreateConstraintStatements(table);
        }
    }