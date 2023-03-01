package com.github.grooviter.matter.tablesaw.test

trait NumericAware {

    static int[] zeros(int size) {
        return (1..size).collect { 0 }
    }

    static double[] zerosAsDouble(int size) {
        return zeros(size).collect { it.doubleValue() }
    }
}
