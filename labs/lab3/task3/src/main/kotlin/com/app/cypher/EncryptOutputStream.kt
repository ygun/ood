package com.app.cypher

import java.io.OutputStream

class EncryptOutputStream(
    private val stream: OutputStream,
    private val key: Long
) : OutputStream() {

    override fun write(b: Int) {
        val encryptedByte = getSubstitutionTable(key).getValue(b)
        stream.write(encryptedByte)
    }

    override fun flush() = stream.flush()
}
