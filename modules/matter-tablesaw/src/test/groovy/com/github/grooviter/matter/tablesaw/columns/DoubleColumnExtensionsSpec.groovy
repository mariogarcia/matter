package com.github.grooviter.matter.tablesaw.columns

import com.github.grooviter.matter.tablesaw.BaseSpec
import tech.tablesaw.columns.Column

class DoubleColumnExtensionsSpec extends BaseSpec {
    void 'query column with values in numeric range'() {
        when:
        Column ids = foodTable[foodTable['SUGAR'] in 0.2..0.3, "ID"]

        then:
        ids.size() == 20
    }

    void 'query column combining column values in range and another criteria'() {
        when:
        Column ids = foodTable[foodTable['SUGAR'] in 0.2..0.3 & foodTable['CARBS'] <= 2, "ID"]

        then:
        ids.size() == 9
    }
}
