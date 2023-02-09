package com.github.grooviter.matter.tablesaw

import tech.tablesaw.api.Table
import tech.tablesaw.columns.Column

class TableExtensionsSpec extends BaseSpec {
    void 'column rows by iloc(row0...rowN, colName)'() {
        when:
        Column idColumn = table.iloc(0..2, "ID")

        then:
        idColumn.name() == 'ID'

        and:
        idColumn.size() == 3
    }

    void 'column rows by iloc(row0...rowN, colIndex)'() {
        when:
        Column nameColumn = table.iloc(0..2, 1)

        then:
        nameColumn.name() == 'NAME'

        and:
        nameColumn.size() == 3
    }

    void 'table rows by iloc(row0...rowN, [colName0, colName1, colNameN])'() {
        when:
        Table partial = table.iloc(0..2, ["ID", "NAME"])

        then:
        partial.columnNames() == ['ID', 'NAME']

        and:
        partial.size() == 3
    }

    void 'table rows by iloc(row0...rowN, [colIndex0, colIndex1, colIndexN])'() {
        when:
        Table partial = table.iloc(0..2, [0, 2])

        then:
        partial.columnNames() == ['ID', 'BRAND ID']

        and:
        partial.size() == 3
    }

    void 'table rows by [row0...rowN] operator'() {
        when:
        Table partial = table[0..2]

        then:
        partial.columnNames().size() == 20

        and:
        partial.size() == 3
    }

    void 'table rows by [row0...rowN, [colName0, colName1, colName2] operator'() {
        when:
        Table partial = table[0..2, ["ID", "NAME"]]

        then:
        partial.columnNames() == ['ID', 'NAME']

        and:
        partial.size() == 3
    }

    void 'table rows by [row0..rowN, [colIndex0, colIndex1, colIndexN]] operator'() {
        when:
        Table partial = table[0..2, [1]]

        then:
        partial.columnNames() == ['NAME']

        and:
        partial.size() == 3
    }

    void 'column rows by [row0...rowN, colName] operator'() {
        when:
        Column idColumn = table[0..2, "ID"]

        then:
        idColumn.name() == 'ID'

        and:
        idColumn.size() == 3
    }

    void 'table rows by [row0..rowN, col...col] operator'() {
        when:
        Table partial = table[0..2, 0..1]

        then:
        partial.columnNames() == ['ID', 'NAME']

        and:
        partial.size() == 3
    }

    void 'column rows by [row0...rowN, colIndex] operator'() {
        when:
        Column partial = table[0..2, 1]

        then:
        partial.name() == 'NAME'

        and:
        partial.size() == 3
    }

    void 'add column by table[colName] = table[colName] operator'() {
        given:
        Table partial = table.iloc(0..3, ["NAME"])
        Integer initColSize = partial.columnCount()

        when:
        partial['NAME_AGAIN'] = partial["NAME"]

        then:
        partial['NAME_AGAIN'].size() == partial['NAME'].size()

        and:
        partial.columnCount() == initColSize + 1
    }
}
