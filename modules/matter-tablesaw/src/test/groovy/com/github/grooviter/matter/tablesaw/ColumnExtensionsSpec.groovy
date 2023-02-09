package com.github.grooviter.matter.tablesaw

import spock.lang.Unroll
import tech.tablesaw.columns.Column

class ColumnExtensionsSpec extends BaseSpec {
    @Unroll
    def 'apply arithmetic ops (#id)'(Closure<Column> operation, BigDecimal expectedResult) {
        when:
        def transformedCol = operation(table[0..10, 'SUGAR'])

        then:
        transformedCol[5] == expectedResult

        where:
        id         | operation            | expectedResult
        'multiply' | { col -> col * 2 }   | 1
        'minus'    | { col -> col - 0.5 } | 0
        'add'      | { col -> col + 2 }   | 2.5
        'division' | { col -> col / 2 }   | 0.25
        'reminder' | { col -> col % 2 }   | 0.5
        'power'    | { col -> col ** 2 }  | 0.25
    }
}
