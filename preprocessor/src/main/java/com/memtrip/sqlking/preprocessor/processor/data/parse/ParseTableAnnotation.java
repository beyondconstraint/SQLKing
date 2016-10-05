package com.memtrip.sqlking.preprocessor.processor.data.parse;

import com.memtrip.sqlking.common.ConflictAction;

import com.memtrip.sqlking.common.TriggerType;
import com.memtrip.sqlking.common.TriggerTime;
import com.memtrip.sqlking.common.SortOrder;

import com.memtrip.sqlking.preprocessor.processor.Context;
import com.memtrip.sqlking.preprocessor.processor.data.Column;
import com.memtrip.sqlking.preprocessor.processor.data.PrimaryKey;
import com.memtrip.sqlking.preprocessor.processor.data.ForeignKey;
import com.memtrip.sqlking.preprocessor.processor.data.Index;
import com.memtrip.sqlking.preprocessor.processor.data.IndexColumn;
import com.memtrip.sqlking.preprocessor.processor.data.Table;
import com.memtrip.sqlking.preprocessor.processor.data.Constraint;
import com.memtrip.sqlking.preprocessor.processor.data.Trigger;

import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import java.util.ArrayList;
import java.util.List;
import freemarker.ext.beans.StringModel;

import static com.memtrip.sqlking.preprocessor.processor.data.parse.ParseColumnAnnotation.parseColumn;

class ParseTableAnnotation
    {
    static Table parseTable(Element element)
        {
        String name                     = assembleName(element);
        String tablePackage             = assemblePackage(element);
        String type                     = tablePackage + "." + name;

        System.out.println("++ Preprocessing table: " + type);

        List<Column> columns            = assembleColumns(element);
        PrimaryKey primaryKey           = assemblePrimaryKey(element);
        List<ForeignKey> foreignKeys    = assembleForeignKeys(element);
        List<Index> indexes             = assembleIndexes(element, columns);
        List<Constraint> constraints    = assembleConstraints(element);
        List<Trigger> triggers          = assembleTriggers(element);

        Table table = new Table();

        table.setElement(element);
        table.setName(name);
        table.setPackage(tablePackage);
        table.setType(type);
        table.setColumns(columns);
        table.setPrimaryKey(primaryKey);
        table.setForeignKeys(foreignKeys);
        table.setIndexes(indexes);
        table.setConstraints (constraints);
        table.setTriggers (triggers);

        return table;
    }

    private static String assembleName(Element element) {
        Name name = element.getSimpleName();
        return name.toString();
    }

    private static String assemblePackage(Element element) {
        PackageElement packageElement = Context.getInstance().getElementUtils().getPackageOf(element);
        Name name = packageElement.getQualifiedName();
        return name.toString();
    }

    private static List<Column> assembleColumns(Element element) {
        List<Column> columns = new ArrayList<>();

        if (element.getEnclosedElements() != null && element.getEnclosedElements().size() > 0) {
            for (Element childElement : element.getEnclosedElements()) {
                if (childElement.getKind().isField() && childElement.getAnnotation(com.memtrip.sqlking.common.Column.class) != null) {
                    columns.add(parseColumn(childElement));
                }
            }
        }
        return columns;
    }

    private static PrimaryKey assemblePrimaryKey(Element element) {
        com.memtrip.sqlking.common.Table tableAnnotation = element.getAnnotation(com.memtrip.sqlking.common.Table.class);
        com.memtrip.sqlking.common.PrimaryKey primaryKeyAnnotation = tableAnnotation.primaryKey();

        if (primaryKeyAnnotation != null && primaryKeyAnnotation.active() == true)
            {
            PrimaryKey primaryKey = new PrimaryKey();

            primaryKey.setColumns(primaryKeyAnnotation.columns());
            primaryKey.setAutoNumber(primaryKeyAnnotation.auto_increment());
            primaryKey.setOnConflict (primaryKeyAnnotation.onConflict());

            return primaryKey;
            }
        return null;
    }

    private static List<Index> assembleIndexes (Element element, List<Column> columns)
        {
        com.memtrip.sqlking.common.Table tableAnnotation = element.getAnnotation(com.memtrip.sqlking.common.Table.class);
        com.memtrip.sqlking.common.Index[] indexesAnnotation = tableAnnotation.indexes();

        String tableName = element.getSimpleName().toString();

        List<Index> indexes = new ArrayList<>();

        for (com.memtrip.sqlking.common.Index indexAnnotation : indexesAnnotation)
            {
            Index index = new Index();

            index.setTableName(tableName);
            index.setIndexName(indexAnnotation.indexName());
            index.setUnique(indexAnnotation.unique());

            com.memtrip.sqlking.common.IndexColumn[] indexColumnsAnnotation = indexAnnotation.columns();

            List<IndexColumn> idxColumns = new ArrayList<>();

            for (com.memtrip.sqlking.common.IndexColumn columnAnnotation : indexColumnsAnnotation)
                {
                IndexColumn indexColumn = new IndexColumn();

                indexColumn.setColumn(columnAnnotation.column());
                indexColumn.setSortOrder(columnAnnotation.sortOrder());

                idxColumns.add(indexColumn);
                }

            index.setColumns(idxColumns);

            indexes.add(index);
            }

        for (Column column : columns)
            {
            if (column.isIndex())
                {
                Index index = new Index();

                index.setTableName(tableName);
                index.setIndexName(column.getName());
                index.setUnique(false);

                List<IndexColumn> idxColumns = new ArrayList<>();

                IndexColumn columnIndex = new IndexColumn();

                columnIndex.setColumn(column.getName());
                columnIndex.setSortOrder(SortOrder.ASC);

                idxColumns.add(columnIndex);
                index.setColumns(idxColumns);

                indexes.add(index);
                }
            }

        return indexes;
        }

    private static List<ForeignKey> assembleForeignKeys(Element element)
        {
        com.memtrip.sqlking.common.Table tableAnnotation = element.getAnnotation(com.memtrip.sqlking.common.Table.class);
        com.memtrip.sqlking.common.ForeignKey[] foreignKeysAnnotation = tableAnnotation.foreignKeys();

        List<ForeignKey> foreignKeys = new ArrayList<>();

        for (com.memtrip.sqlking.common.ForeignKey fkAnnotation : foreignKeysAnnotation)
            {
            ForeignKey foreignKey = new ForeignKey();

            foreignKey.setForeignTableName(fkAnnotation.foreignTableName());
            foreignKey.setForeignTableAliasName (fkAnnotation.foreignTableAlias());

            List<String> foreignColumnNames = new ArrayList<>();
            for (String columnName : fkAnnotation.foreignColumnNames())
                {
                foreignColumnNames.add(columnName);
                }
            foreignKey.setForeignColumnNames(foreignColumnNames);

            List<String> localColumnNames = new ArrayList<>();
            for (String columnName : fkAnnotation.localColumnNames())
                {
                localColumnNames.add(columnName);
                }

            foreignKey.setLocalColumnNames(localColumnNames);
            foreignKeys.add(foreignKey);
            }

        return foreignKeys;
        }

    private static List<Constraint> assembleConstraints(Element element)
        {
        com.memtrip.sqlking.common.Table tableAnnotation = element.getAnnotation(com.memtrip.sqlking.common.Table.class);
        com.memtrip.sqlking.common.Constraint[] constraintsAnnotation = tableAnnotation.constraints();

        List<Constraint> constraints = new ArrayList<>();

        for (com.memtrip.sqlking.common.Constraint constraintAnnotation : constraintsAnnotation)
            {
            Constraint constraint = new Constraint();

            constraint.setConstraintName(constraintAnnotation.constraintName());
            constraint.setConstraintExpression(constraintAnnotation.expression());
            constraint.setOnConflict(constraintAnnotation.onConflict());

            constraints.add(constraint);
            }

        return constraints;
        }

    private static List<Trigger> assembleTriggers(Element element)
        {
        com.memtrip.sqlking.common.Table tableAnnotation = element.getAnnotation(com.memtrip.sqlking.common.Table.class);
        com.memtrip.sqlking.common.Trigger[] triggersAnnotation = tableAnnotation.triggers();

        List<Trigger> triggers = new ArrayList<>();

        if (triggersAnnotation != null && triggersAnnotation.length > 0)
            {
            for (com.memtrip.sqlking.common.Trigger triggerAnnotation : triggersAnnotation)
                {
                Trigger trigger = new Trigger();

                trigger.setTriggerName(triggerAnnotation.triggerName());
                trigger.setTriggerTime(triggerAnnotation.triggerTime());
                trigger.setTriggerType(triggerAnnotation.triggerType());

                List<String> onUpdateColumns = new ArrayList<>();

                for (String columnName : triggerAnnotation.updateOfColumnNames())
                    {
                    onUpdateColumns.add(columnName);
                    }

                trigger.setOnUpdateOfColumnNames(onUpdateColumns);

                trigger.setForEachRow(triggerAnnotation.forEachRow());
                trigger.setWhenExpression(triggerAnnotation.whenExpression());
                trigger.setStatement(triggerAnnotation.statement());

                triggers.add(trigger);
                }
            }

        return triggers;
        }

    }