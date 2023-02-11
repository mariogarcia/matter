package com.github.grooviter.matter.tablesaw

import groovy.transform.CompileStatic
import tech.tablesaw.api.BooleanColumn
import tech.tablesaw.api.ColumnType
import tech.tablesaw.api.DateColumn
import tech.tablesaw.api.DateTimeColumn
import tech.tablesaw.api.DoubleColumn
import tech.tablesaw.api.FloatColumn
import tech.tablesaw.api.InstantColumn
import tech.tablesaw.api.IntColumn
import tech.tablesaw.api.LongColumn
import tech.tablesaw.api.ShortColumn
import tech.tablesaw.api.StringColumn
import tech.tablesaw.api.Table
import tech.tablesaw.api.TextColumn
import tech.tablesaw.api.TimeColumn
import tech.tablesaw.columns.Column
import tech.tablesaw.selection.Selection

/**
 * Groovy extensions for Tablesaw (https://github.com/jtablesaw/tablesaw)
 *
 * @since 1.0.0
 */
@CompileStatic
@SuppressWarnings('unused')
class TableExtensions {
    static Table iloc(Table source, IntRange indexes, List cols) {
        return byIntRangeAndColumnList(source, indexes, cols)
    }

    static <U> Column<U> iloc(Table source, IntRange indexRange, String column) {
        return byIndexRangeAndStringColumn(source, indexRange, column)
    }

    static <U> Column<U> iloc(Table source, IntRange indexRange, Integer column) {
        return byIndexRangeAndIntegerColumn(source, indexRange, column)
    }

    static Column<?> getAt(Table source, String columnName) {
        return source.column(columnName)
    }

    static Table getAt(Table source, IntRange indexRange) {
        return source.rows(indexRange.toList() as int[])
    }

    static Table getAt(Table source, IntRange indexRange, List cols) {
        return byIntRangeAndColumnList(source, indexRange, cols)
    }

    static Table getAt(Table source, Selection filter) {
        return source.where(filter)
    }

    static Table getAt(Table source, Selection filter, List cols) {
        return source.where(filter).retainColumns(cols as String[])
    }

    static <U> Column <U> getAt(Table source, Selection filter, String col) {
        return source.where(filter).column(col) as Column<U>
    }

    static Table getAt(Table source, IntRange indexRange, IntRange columnIndex) {
        List<Column<?>> columns = source.columns(columnIndex.toList() as int[])
        return source.rows(indexRange.toList() as int[]).select(columns*.name() as String[])
    }

    static <U> Column<U> getAt(Table source, IntRange indexRange, String column) {
        return byIndexRangeAndStringColumn(source, indexRange, column)
    }

    static <U> Column<U> getAt(Table source, IntRange indexRange, Integer column) {
        return byIndexRangeAndIntegerColumn(source, indexRange, column)
    }

    static <U> Table putAt(Table source, String key, Column<U> replaceBy) {
        return source.addColumns(resolveColumn(key, replaceBy).append(replaceBy) as Column<?>[])
    }

    static Table minus(Table source, Column<?> columnToDelete) {
        return source.removeColumns(columnToDelete)
    }

    private static <U> Column<U> byIndexRangeAndStringColumn(Table source, IntRange indexRange, String column) {
        return source.column(column).subset(indexRange as int[]) as Column<U>
    }

    private static <U> Column<U> byIndexRangeAndIntegerColumn(Table source, IntRange indexRange, Integer column) {
        return source.column(column).subset(indexRange as int[]) as Column<U>
    }

    private static Table byIntRangeAndColumnList(Table source, IntRange indexRange, List cols) {
        boolean areNumbers = cols.every { it instanceof Integer }
        boolean areNames = cols.every { it instanceof String }

        if (!(areNumbers || areNames)) {
            throw new RuntimeException("cols should either column names or index numbers")
        }

        if (areNumbers) {
            Column<?>[] columns = source.columns(cols as int[])
            return source.rows(indexRange.toList() as int[]).select(columns*.name() as String[])
        }

        return source.rows(indexRange.toList() as int[]).select(cols*.toString() as String[])
    }

    private static <T> Column<T> resolveColumn(String name, Column<T> source) {
        switch(source.type()) {
            case ColumnType.STRING: return StringColumn.create(name) as Column<T>
            case ColumnType.INTEGER: return IntColumn.create(name) as Column<T>
            case ColumnType.DOUBLE: return DoubleColumn.create(name) as Column<T>
            case ColumnType.BOOLEAN: return BooleanColumn.create(name) as Column<T>
            case ColumnType.FLOAT: return FloatColumn.create(name) as Column<T>
            case ColumnType.INSTANT: return InstantColumn.create(name) as Column<T>
            case ColumnType.LOCAL_DATE: return DateColumn.create(name) as Column<T>
            case ColumnType.LOCAL_DATE_TIME: return DateTimeColumn.create(name) as Column<T>
            case ColumnType.LOCAL_TIME: return TimeColumn.create(name) as Column<T>
            case ColumnType.LONG: return LongColumn.create(name) as Column<T>
            case ColumnType.SHORT: return ShortColumn.create(name) as Column<T>
            case ColumnType.TEXT: return TextColumn.create(name) as Column<T>
            default:
                return StringColumn.create(name) as Column<T>
        }
    }
}