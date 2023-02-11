package com.github.grooviter.matter.tablesaw

import tech.tablesaw.api.DateColumn
import tech.tablesaw.api.DoubleColumn
import tech.tablesaw.api.StringColumn
import tech.tablesaw.columns.Column
import tech.tablesaw.selection.Selection

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

    static DateColumn getDate(Column<?> source) {
        return source as DateColumn
    }

    static StringColumn getString(Column<?> source) {
        return source as StringColumn
    }

    static DoubleColumn getD(Column<?> source){
        return source as DoubleColumn
    }

    static <T> Column<T> where(Column<T> source, Closure<Selection> fn) {
        return source.where(fn(source) as Selection)
    }
}
