package com.app.cypher

import java.io.InputStream

class DecryptInputStream(
    private val stream: InputStream,
    private val key: Long
) : InputStream() {

    override fun read(): Int = getReversedSubstitutionTable(key).getValue(stream.read())
}
