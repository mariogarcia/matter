package com.github.grooviter.matter.io

import com.github.grooviter.matter.Matter as mt
import com.github.grooviter.matter.tablesaw.test.BaseSpec
import tech.tablesaw.api.Table

class ReadProxyExtensionsSpec extends BaseSpec {
    void 'read a csv from matter import'() {
        when:
        Table table = mt.read().csv(FOOD_CSV_PATH, sep: ';')

        then:
        table.rowCount() == 3197
    }
}
