package com.github.grooviter.matter.tablesaw

import tech.tablesaw.api.DateColumn
import tech.tablesaw.api.DateTimeColumn
import tech.tablesaw.api.DoubleColumn
import tech.tablesaw.api.IntColumn
import tech.tablesaw.api.NumberColumn
import tech.tablesaw.api.TextColumn
import tech.tablesaw.columns.Column
import tech.tablesaw.selection.Selection

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.function.Function

@SuppressWarnings('unused')
class ColumnExtensions {

    static <T> Column<T> minus(Column<T> source, Number number) {
        return source.map { it - number } as Column<T>
    }

    static <T> Column<T> plus(Column<T> source, Number number) {
        return source.map { it + number } as Column<T>
    }

    static <T> Column<T> div(Column<T> source, Number number) {
        return source.map { it / number } as Column<T>
    }

    static <T> Column<T> mod(Column<T> source, Number number) {
        return source.map { it % number } as Column<T>
    }

    static <T> Column<T> where(Column<T> source, Closure<Selection> fn) {
        return source.where(fn(source) as Selection)
    }

    static <U> IntColumn mapToInt(Column<U> column, Function<U, Integer> function) {
        return column.mapInto(function, IntColumn.create("temporalIntColumn", column.size()))
    }

    static <U> DoubleColumn mapToDouble(Column<U> column, Function<U, Double> function) {
        return column.mapInto(function, DoubleColumn.create("temporalDoubleColumn", column.size()))
    }

    static <U> TextColumn mapToText(Column<U> column, Function<U, String> function) {
        return column.mapInto(function, TextColumn.create("temporalTextColumn", column.size()))
    }

    static <U> DateColumn mapToDate(Column<U> column, Function<U, LocalDate> function) {
        return column.mapInto(function, DateColumn.create("temporalDateColumn", column.size()))
    }

    static <U> DateTimeColumn mapToDateTime(Column<U> column, Function<U, LocalDateTime> function) {
        return column.mapInto(function, DateTimeColumn.create("temporalDateColumn", column.size()))
    }

    static Column minus(Column source, Column other) {
        if (source instanceof NumberColumn && other instanceof NumberColumn){
            return source.subtract(other)
        }
        throw new RuntimeException("can't subtract NON number columns")
    }
}
