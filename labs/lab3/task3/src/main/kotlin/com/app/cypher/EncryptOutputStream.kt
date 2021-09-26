package com.app.cypher

import java.io.OutputStream

class EncryptOutputStream(
    private val stream: OutputStream,
    private val key: Long
) : OutputStream() {

    override fun write(b: Int) = stream.write(getSubstitutionTable(key).getValue(b))

    override fun flush() = stream.flush()
}
