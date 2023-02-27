package com.github.grooviter.matter.tablesaw.columns

import tech.tablesaw.api.NumberColumn
import tech.tablesaw.selection.Selection

class NumberColumnExtensions {
    static Selection between(NumberColumn numberColumn, NumberRange range) {
        Number from = (Number) range.from
        Number to = (Number) range.to
        return numberColumn.isBetweenInclusive(from.doubleValue(), to.doubleValue())
    }

    static Selection between(NumberColumn numberColumn, IntRange range) {
        return numberColumn.isBetweenInclusive(range.from, range.to)
    }
}
