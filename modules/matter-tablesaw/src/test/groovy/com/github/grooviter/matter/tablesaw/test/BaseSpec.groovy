package com.github.grooviter.matter.tablesaw.test

import spock.lang.Shared
import spock.lang.Specification
import tech.tablesaw.api.ColumnType
import tech.tablesaw.api.Table
import tech.tablesaw.io.csv.CsvReadOptions

class BaseSpec extends Specification {
    @Shared Table foodTable
    @Shared Table ratesTable

    void setup() {
        def foodOptions = CsvReadOptions.builder("src/test/resources/data/food.csv")
                .separator(';' as char)
                .header(true)
                .missingValueIndicator('NaN')

        foodTable = Table.read().csv(foodOptions)

        def ratesOptions = CsvReadOptions.builder("src/test/resources/data/interest-rates.csv")
                .separator(',' as char)
                .header(true)
        .dateTimeFormat("yyyy-MM-dd HH:mm:ss.SSS x")
                .columnTypes(
                    ColumnType.DOUBLE,
                    ColumnType.DOUBLE,
                    ColumnType.DOUBLE,
                    ColumnType.DOUBLE,
                    ColumnType.DOUBLE,
                    ColumnType.DOUBLE,
                    ColumnType.DOUBLE,
                    ColumnType.LOCAL_DATE_TIME,
                    ColumnType.DOUBLE,
                    ColumnType.DOUBLE,
                    ColumnType.DOUBLE)
                .missingValueIndicator('NaN')

        ratesTable = Table.read().csv(ratesOptions)
    }
}
