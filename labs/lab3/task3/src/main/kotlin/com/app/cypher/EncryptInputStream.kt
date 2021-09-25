package com.app.cypher

import java.io.InputStream

class EncryptInputStream(
    private val stream: InputStream,
    private val key: Long
) : InputStream() {

    override fun read(): Int = getSubstitutionTable(key).getValue(stream.read())
}
