package com.memtrip.sqlking.preprocessor.processor.data.parse;

import com.memtrip.sqlking.preprocessor.processor.data.Column;
import com.memtrip.sqlking.preprocessor.processor.data.Constraint;
import com.memtrip.sqlking.preprocessor.processor.data.ForeignKey;

import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;
import java.util.ArrayList;
import java.util.List;

class ParseColumnAnnotation {

    static Column parseColumn(Element element) {

        String name = assembleName(element);
        String type = assembleType(element);
        String className = assembleClassName(type);

        boolean isIndex = assembleIsIndex(element);
        boolean hasPrimaryKey = assemblePrimaryKey(element);
        boolean hasAutoIncrement = assembleAutoIncrement(element);
        boolean isNotNotNull = assembleNotNull(element);
        String defaultValue = assembleDefaultValue(element);
        ForeignKey foreignKey = assembleForeignKey(element);
        List<Constraint> constraints = assembleConstraints(element);

        Column column = new Column();
        column.setName(name);
        column.setType(type);
        column.setClassName(className);
        column.setIsIndex(isIndex);
        column.setHasPrimaryKey(hasPrimaryKey);
        column.setHasAutoIncrement(hasAutoIncrement);
        column.setNotNull(isNotNotNull);
        column.setDefaultValue(defaultValue);
        column.setConstraints(constraints);
        column.setForeignKey(foreignKey);
        column.setConstraints(constraints);

        return column;
    }

    private static String assembleName(Element element) {
        Name name = element.getSimpleName();
        return name.toString();
    }

    private static String assembleType(Element element) {
        TypeMirror typeMirror = element.asType();
        return typeMirror.toString();
    }

    private static String assembleClassName(String type) {
        String[] packageParts = type.split("\\.");
        return packageParts[packageParts.length - 1];
    }

    private static boolean assembleIsIndex(Element element) {
        com.memtrip.sqlking.common.Column column = element.getAnnotation(com.memtrip.sqlking.common.Column.class);
        return column != null && column.index();
    }

    private static boolean assemblePrimaryKey(Element element) {
        com.memtrip.sqlking.common.Column column = element.getAnnotation(com.memtrip.sqlking.common.Column.class);
        return column.primary_key();
    }

    private static boolean assembleAutoIncrement(Element element) {
        com.memtrip.sqlking.common.Column column = element.getAnnotation(com.memtrip.sqlking.common.Column.class);
        return column.auto_increment();
    }

    private static boolean assembleNotNull(Element element) {
        com.memtrip.sqlking.common.Column column = element.getAnnotation(com.memtrip.sqlking.common.Column.class);
        return column.not_null();
    }

    private static String assembleDefaultValue(Element element) {
        com.memtrip.sqlking.common.Column column = element.getAnnotation(com.memtrip.sqlking.common.Column.class);
        return column.defaultValue();
    }

    private static ForeignKey assembleForeignKey(Element element) {
        com.memtrip.sqlking.common.Column column = element.getAnnotation(com.memtrip.sqlking.common.Column.class);
        com.memtrip.sqlking.common.ForeignKey foreignKeyAnnotation = column.foreignKey();
    
        ForeignKey foreignKey = new ForeignKey();

        foreignKey.setForeignTableName(foreignKeyAnnotation.foreignTableName());
        
        List<String> localColumnNames = new ArrayList<>();
    
        for (String columnName : foreignKeyAnnotation.localColumnNames())
            {
            localColumnNames.add(columnName);
            }

        List<String> foreignColumnNames = new ArrayList<>();

        for (String columnName : foreignKeyAnnotation.foreignColumnNames())
            {
            foreignColumnNames.add(columnName);
            }

        foreignKey.setRIUpdateRule(foreignKeyAnnotation.updateRule());
        foreignKey.setRIDeleteRule(foreignKeyAnnotation.deleteRule());

        return foreignKey;
    }

    private static List<Constraint> assembleConstraints(Element element) {
        com.memtrip.sqlking.common.Column column = element.getAnnotation(com.memtrip.sqlking.common.Column.class);
        com.memtrip.sqlking.common.Constraint[] constraintsAnnotation = column.constraints();

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

}
