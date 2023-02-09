package com.github.grooviter.matter.tablesaw

import spock.lang.Shared
import spock.lang.Specification
import tech.tablesaw.api.Table
import tech.tablesaw.io.csv.CsvReadOptions

class BaseSpec extends Specification {
    @Shared Table table

    void setup() {
        def options = CsvReadOptions.builder("src/test/resources/data/food.csv")
                .separator(';' as char)
                .header(true)
                .missingValueIndicator('NaN')

        table = Table.read().csv(options)
    }
}
