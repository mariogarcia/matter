package com.github.grooviter.matter.tablesaw.columns

import tech.tablesaw.api.StringColumn
import tech.tablesaw.selection.Selection

class StringColumnExtensions {
    static Selection matches(StringColumn column, String regex) {
        return column.matchesRegex(regex)
    }
}
