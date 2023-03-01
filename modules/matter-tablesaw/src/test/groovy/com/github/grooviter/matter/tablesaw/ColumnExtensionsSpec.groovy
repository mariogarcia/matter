package com.github.grooviter.matter.tablesaw

import com.github.grooviter.matter.tablesaw.test.BaseSpec
import spock.lang.Unroll
import tech.tablesaw.api.DateColumn
import tech.tablesaw.api.DoubleColumn
import tech.tablesaw.api.IntColumn
import tech.tablesaw.columns.Column

import java.time.LocalDate
import java.time.LocalDateTime

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

    def 'transform any column to IntColumn'() {
        given:
        def table = foodTable[0..100]

        when:
        table['IS_SALSA'] = table['SUBGROUP NAME'].mapToInt { it == 'Salsas' ? 1 : 0 }

        then:
        table['IS_SALSA'].size() == 101
        table['IS_SALSA'] instanceof IntColumn
    }

    def 'transform any column to DoubleColumn'() {
        given:
        def table = foodTable[0..100]

        when:
        table['IS_SALSA'] = table['SUBGROUP NAME'].mapToDouble { "$it".size().doubleValue() }

        then:
        table['IS_SALSA'].size() == 101
        table['IS_SALSA'] instanceof DoubleColumn
    }

    def 'transform any column to DateColumn'() {
        given:
        def table = ratesTable[0..100]
        def toText =  { LocalDateTime time -> time.format("yyyy/MM/dd") }
        def toDate =  { String date -> LocalDate.parse(date, "yyyy/MM/dd") }
        def pointInTime = LocalDate.parse('1995/01/01', "yyyy/MM/dd")

        when:
        table['Date'] = table['time'].mapToText(toText).mapToDate(toDate)

        and:
        table = table[table['Date'] > pointInTime, ["Date"]]

        then:
        table.rowCount() == 41
        table['Date'] instanceof DateColumn
    }
}
