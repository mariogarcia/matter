package com.github.grooviter.matter.tablesaw

import spock.lang.Shared
import spock.lang.Specification
import tech.tablesaw.api.Table
import tech.tablesaw.io.csv.CsvReadOptions

class TablesawExtensionsSpec extends Specification {

    @Shared Table table

    void setup() {
        def options = CsvReadOptions.builder("src/test/resources/data/food.csv")
            .separator(';' as char)
            .header(true)
            .missingValueIndicator('NaN')

        table = Table.read().csv(options)
    }

    void 'pandas: access rows by iloc(row0...rowN, colName)'() {
        when:
        Table partial = table.iloc(0..2, "ID")

        then:
        partial.columnNames() == ['ID']

        and:
        partial.size() == 3
    }

    void 'pandas: access rows by iloc(row0...rowN, colIndex)'() {
        when:
        Table partial = table.iloc(0..2, 1)

        then:
        partial.columnNames() == ['NAME']

        and:
        partial.size() == 3
    }

    void 'pandas: access rows by iloc(row0...rowN, [colName0, colName1, colNameN])'() {
        when:
        Table partial = table.iloc(0..2, ["ID", "NAME"])

        then:
        partial.columnNames() == ['ID', 'NAME']

        and:
        partial.size() == 3
    }

    void 'pandas: access rows by iloc(row0...rowN, [colIndex0, colIndex1, colIndexN])'() {
        when:
        Table partial = table.iloc(0..2, [0, 2])

        then:
        partial.columnNames() == ['ID', 'BRAND ID']

        and:
        partial.size() == 3
    }

    void 'pandas: access rows by [row0...rowN] operator'() {
        when:
        Table partial = table[0..2]

        then:
        partial.columnNames().size() == 20

        and:
        partial.size() == 3
    }

    void 'pandas: access rows by [row0...rowN, [colName0, colName1, colName2] operator'() {
        when:
        Table partial = table[0..2, ["ID", "NAME"]]

        then:
        partial.columnNames() == ['ID', 'NAME']

        and:
        partial.size() == 3
    }

    void 'pandas: access rows by [row0..rowN, [colIndex0, colIndex1, colIndexN]] operator'() {
        when:
        Table partial = table[0..2, [1]]

        then:
        partial.columnNames() == ['NAME']

        and:
        partial.size() == 3
    }

    void 'pandas: access rows by [row0...rowN, colName] operator'() {
        when:
        Table partial = table[0..2, "ID"]

        then:
        partial.columnNames() == ['ID']

        and:
        partial.size() == 3
    }

    void 'pandas: access rows by [row0..rowN, col...col] operator'() {
        when:
        Table partial = table[0..2, 0..1]

        then:
        partial.columnNames() == ['ID', 'NAME']

        and:
        partial.size() == 3
    }

    void 'pandas: access rows by [row0...rowN, colIndex] operator'() {
        when:
        Table partial = table[0..2, 1]

        then:
        partial.columnNames() == ['NAME']

        and:
        partial.size() == 3
    }

    void 'pandas: add column by table[colName] = table[colName] operator'() {
        given:
        Table partial = table.iloc(0..3, "NAME")
        Integer initColSize = partial.columnCount()

        when:
        partial['NAME_AGAIN'] = partial["NAME"]

        then:
        partial['NAME_AGAIN'].size() == partial['NAME'].size()

        and:
        partial.columnCount() == initColSize + 1
    }
}
