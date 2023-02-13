package com.github.grooviter.matter.tablesaw

import tech.tablesaw.api.DoubleColumn
import tech.tablesaw.api.Table
import tech.tablesaw.columns.Column
import tech.tablesaw.selection.Selection

class TableExtensionsSpec extends BaseSpec {
    void 'column rows by iloc(row0...rowN, colName)'() {
        when:
        Column idColumn = foodTable.iloc(0..2, "ID")

        then:
        idColumn.name() == 'ID'

        and:
        idColumn.size() == 3
    }

    void 'column rows by iloc(row0...rowN, colIndex)'() {
        when:
        Column nameColumn = foodTable.iloc(0..2, 1)

        then:
        nameColumn.name() == 'NAME'

        and:
        nameColumn.size() == 3
    }

    void 'table rows by iloc(row0...rowN, [colName0, colName1, colNameN])'() {
        when:
        Table partial = foodTable.iloc(0..2, ["ID", "NAME"])

        then:
        partial.columnNames() == ['ID', 'NAME']

        and:
        partial.size() == 3
    }

    void 'table rows by iloc(row0...rowN, [colIndex0, colIndex1, colIndexN])'() {
        when:
        Table partial = foodTable.iloc(0..2, [0, 2])

        then:
        partial.columnNames() == ['ID', 'BRAND ID']

        and:
        partial.size() == 3
    }

    void 'table rows by [row0...rowN] operator'() {
        when:
        Table partial = foodTable[0..2]

        then:
        partial.columnNames().size() == 20

        and:
        partial.size() == 3
    }

    void 'table rows by [row0...rowN, [colName0, colName1, colName2] operator'() {
        when:
        Table partial = foodTable[0..2, ["ID", "NAME"]]

        then:
        partial.columnNames() == ['ID', 'NAME']

        and:
        partial.size() == 3
    }

    void 'table rows by [row0..rowN, [colIndex0, colIndex1, colIndexN]] operator'() {
        when:
        Table partial = foodTable[0..2, [1]]

        then:
        partial.columnNames() == ['NAME']

        and:
        partial.size() == 3
    }

    void 'column rows by [row0...rowN, colName] operator'() {
        when:
        Column idColumn = foodTable[0..2, "ID"]

        then:
        idColumn.name() == 'ID'

        and:
        idColumn.size() == 3
    }

    void 'table rows by [row0..rowN, col...col] operator'() {
        when:
        Table partial = foodTable[0..2, 0..1]

        then:
        partial.columnNames() == ['ID', 'NAME']

        and:
        partial.size() == 3
    }

    void 'column rows by [row0...rowN, colIndex] operator'() {
        when:
        Column partial = foodTable[0..2, 1]

        then:
        partial.name() == 'NAME'

        and:
        partial.size() == 3
    }

    void 'add column by table[colName] = column operator'() {
        given:
        Table partial = foodTable.iloc(0..3, ["NAME"])
        Integer initColSize = partial.columnCount()

        when:
        partial['NAME_AGAIN'] = partial["NAME"]

        then:
        partial['NAME_AGAIN'].size() == partial['NAME'].size()

        and:
        partial.columnCount() == initColSize + 1
    }

    void 'remove column by table[colName] - column operator'() {
        given:
        Table partial = foodTable.iloc(0..3, ["ID", "NAME"])
        Integer initColSize = partial.columnCount()

        when:
        partial = partial - partial["NAME"]

        then:
        partial.columnCount() == initColSize - 1
    }

    void 'table rows filtered by selection table = table[Selection]'() {
        when:
        Table partial = foodTable[foodTable['SUGAR', DoubleColumn].isCloseTo(3.5, 0.05)]

        then:
        partial.size() == 56
    }

    void 'table rows filtered by selection and columns table = table[Selection, [colName1, colNameN]'() {
        when:
        Table partial = foodTable[foodTable['SUGAR', DoubleColumn].isCloseTo(3.5, 0.05), ["ID", "NAME"]]

        then:
        partial.size() == 56
        partial.columnCount() == 2
    }

    void 'column rows filtered by selection and columns col = table[Selection, colName]'() {
        when:
        Column ids = foodTable[foodTable['SUGAR', DoubleColumn].isCloseTo(3.5, 0.05), "ID"]

        then:
        ids.size() == 56
    }

    void 'get typed column from table col = table[colName, colTypeClass]'() {
        when:
        DoubleColumn typedColumn = foodTable['SUGAR', DoubleColumn]
        Selection closeTo3andHalf = typedColumn.isCloseTo(3.5, 0.05)
        Column ids = foodTable[closeTo3andHalf, "ID"]

        then:
        ids.size() == 56
    }
}
