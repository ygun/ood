package com.app.cypher

import java.io.InputStream

class DecryptInputStream(
    private val stream: InputStream,
    private val key: Long
) : InputStream() {

    override fun read(): Int {
        return when (val byte = stream.read()) {
            -1 -> -1
            else -> getReversedSubstitutionTable(key).getValue(byte)
        }
    }
}
