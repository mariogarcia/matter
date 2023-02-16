package com.github.grooviter.matter.tablesaw

import tech.tablesaw.api.NumberColumn
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

    static <T> Column<T> where(Column<T> source, Closure<Selection> fn) {
        return source.where(fn(source) as Selection)
    }

    static Column minus(Column source, Column other) {
        if (source instanceof NumberColumn && other instanceof NumberColumn){
            return source.subtract(other)
        }
        throw new RuntimeException("can't subtract NON number columns")
    }
}
