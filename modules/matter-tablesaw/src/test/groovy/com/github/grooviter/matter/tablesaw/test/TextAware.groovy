package com.github.grooviter.matter.tablesaw.test

trait TextAware {

    static String[] word(int size) {
        return (1..size).collect { "word" }
    }
}