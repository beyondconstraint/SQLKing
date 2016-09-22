package com.memtrip.sqlking.preprocessor.processor.data.parse;

import com.memtrip.sqlking.preprocessor.processor.Context;
import com.memtrip.sqlking.preprocessor.processor.data.Column;
import com.memtrip.sqlking.preprocessor.processor.data.ForeignKey;
import com.memtrip.sqlking.preprocessor.processor.data.Index;
import com.memtrip.sqlking.preprocessor.processor.data.IndexColumn;
import com.memtrip.sqlking.preprocessor.processor.data.Table;

import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import java.util.ArrayList;
import java.util.List;
import freemarker.ext.beans.StringModel;

import static com.memtrip.sqlking.preprocessor.processor.data.parse.ParseColumnAnnotation.parseColumn;

class ParseTableAnnotation {

    static Table parseTable(Element element) {

        String name = assembleName(element);
        String tablePackage = assemblePackage(element);
        String type = tablePackage + "." + name;
        List<Column> columns = assembleColumns(element);
        List<ForeignKey> foreignKeys = assembleForeignKeys(element);
        List<Index>  indexes        = assembleIndexes(element);

        Table table = new Table();
        table.setElement(element);
        table.setName(name);
        table.setPackage(tablePackage);
        table.setType(type);
        table.setColumns(columns);
        table.setForeignKeys(foreignKeys);
        table.setIndexes(indexes);

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

    private static List<Index> assembleIndexes (Element element)
        {
        com.memtrip.sqlking.common.Table tableAnnotation = element.getAnnotation(com.memtrip.sqlking.common.Table.class);
        com.memtrip.sqlking.common.Index[] indexesAnnotation = tableAnnotation.indexes();
        List<Index> indexes = new ArrayList<>();
        for (com.memtrip.sqlking.common.Index indexAnnotation : indexesAnnotation)
            {
            Index index = new Index();
            com.memtrip.sqlking.common.IndexColumn[] indexColumnsAnnotation = indexAnnotation.columns();
            List<IndexColumn> indexColumns = new ArrayList<>();
            for (com.memtrip.sqlking.common.IndexColumn columnAnnotation : indexColumnsAnnotation)
                {
                IndexColumn indexColumn = new IndexColumn();
                indexColumn.setColumn(columnAnnotation.column());
                indexColumn.setSortOrder(columnAnnotation.sortOrder());
                indexColumns.add(indexColumn);
                }
            index.setIndexName(indexAnnotation.indexName());
            index.setColumns(indexColumns);
            index.setUnique(indexAnnotation.unique());
            indexes.add(index);
            }
        return indexes;
        }
    private static List<ForeignKey> assembleForeignKeys(Element element) {
        com.memtrip.sqlking.common.Table tableAnnotation = element.getAnnotation(com.memtrip.sqlking.common.Table.class);
        com.memtrip.sqlking.common.ForeignKey[] foreignKeysAnnotation = tableAnnotation.foreignKeys();

        List<ForeignKey> foreignKeys = new ArrayList<>();

        for (com.memtrip.sqlking.common.ForeignKey fkAnnotation : foreignKeysAnnotation)
            {
            ForeignKey foreignKey = new ForeignKey();
            foreignKey.setForeignTableName(fkAnnotation.foreignTableName());
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
}