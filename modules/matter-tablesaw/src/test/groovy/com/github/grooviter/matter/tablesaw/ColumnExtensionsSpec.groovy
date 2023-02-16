package com.github.grooviter.matter.tablesaw

import spock.lang.Unroll
import tech.tablesaw.columns.Column

class ColumnExtensionsSpec extends BaseSpec {
    @Unroll
    def 'apply arithmetic ops (#id)'(Closure<Column> operation, BigDecimal expectedResult) {
        when:
        def transformedCol = operation(foodTable[0..10, 'SUGAR'])

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

    def 'apply number column subtraction table[col1] - table[col2]'() {
        given:
        foodTable = foodTable.dropRowsWithMissingValues()

        when:
        foodTable["CS_DIFF"] = foodTable["CARBS"] - foodTable["SUGAR"]

        then:
        foodTable["CS_DIFF"].size() == foodTable.size()

        and:
        foodTable[0..0, "CS_DIFF"][0] == foodTable[0..0, "CARBS"][0] - foodTable[0..0, "SUGAR"][0]
    }

    def 'throwing exception when apply not number column subtraction table[col1] - table[col2]'() {
        given:
        foodTable = foodTable.dropRowsWithMissingValues()

        when:
        foodTable["CS_DIFF"] = foodTable["CARBS"] - foodTable["BRAND"]

        then:
        def e = thrown(RuntimeException)
        e.message.contains("NON")
    }
}
