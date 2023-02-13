package com.github.grooviter.matter.tablesaw.columns

import tech.tablesaw.api.DoubleColumn
import tech.tablesaw.api.NumberColumn
import tech.tablesaw.selection.Selection

class DoubleColumnExtensions {
    static Selection inList(NumberColumn numberColumn, Range numbers) {
        return numberColumn.isIn(numbers as Number[])
    }
}
