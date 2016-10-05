package com.memtrip.sqlking.preprocessor.processor.freemarker.method;

import com.memtrip.sqlking.common.TriggerType;
import com.memtrip.sqlking.common.TriggerTime;

import com.memtrip.sqlking.preprocessor.processor.data.Trigger;
import com.memtrip.sqlking.preprocessor.processor.data.Table;

import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssembleCreateTriggersMethod implements TemplateMethodModelEx
    {
    private static final String ASSEMBLE_CREATE_TRIGGERS = "assembleCreateTriggers";

    public static Map<String, Object> getMethodMap ()
        {
        Map<String, Object> map = new HashMap<>();
        map.put(ASSEMBLE_CREATE_TRIGGERS, new AssembleCreateTriggersMethod());
        return map;
        }

    /**
     * Build a list of create Trigger statements based on the provided table
     *
     * @param  table
     * @param triggers The list of triggers that will have create statements created for them
     * @return A SQL statement that will create the triggers (after CREATE TABLE)
     */
    private String buildCreateTriggerStatements (Table table)
        {
        StringBuilder sb = new StringBuilder();

        List<Trigger> triggerList = table.getTriggers();

        for (Trigger trigger : triggerList)
            {
//            sb.append(" CREATE TRIGGER ")
//                    .append(table.getName())
//                    .append("_")
//                    .append(trigger.getTriggerName())
//                    .append(" ")
//                    .append(trigger.getTriggerTime().toString())
//                    .append(" ");
//
//            if (trigger.getOnUpdateOfColumnNames().size() > 0)
//                {
//                sb.append("OF ");
//
//                for (String columnName : trigger.getOnUpdateOfColumnNames())
//                    {
//                    sb.append(columnName);
//                    sb.append(",");
//                    }
//                sb.deleteCharAt(sb.length() -1);
//                }
//
//            sb.append(" ON ")
//                    .append(table.getName());
//
//            if (trigger.isForEachRow())
//                {
//                sb.append(" FOR EACH ROW ");
//                }
//
//            sb.append(trigger.getStatement());
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
            throw new IllegalStateException("The assembleCreateTriggerstatements Table argument must be type of " +
                                            "com.memtrip.sqlking.preprocessor.processor.data.Table");
            }

        return buildCreateTriggerStatements(table);
        }
    }