package com.github.grooviter.matter

import com.github.grooviter.matter.io.ReaderProxy

class Matter {
    static ReaderProxy read() {
        return new ReaderProxy()
    }
}
