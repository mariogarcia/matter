package com.github.grooviter.matter.tablesaw

import tech.tablesaw.columns.Column

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
}
