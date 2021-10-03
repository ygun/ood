package com.app.compress

import java.io.OutputStream

class CompressOutputStream(
    private val stream: OutputStream
) : OutputStream() {

    private var buffer: Pair<Int, Int>? = null

    override fun write(byte: Int) {
        if (buffer == null) {
            addToBuffer(byte)
        } else if (isNeedToWriteBuffer(byte)) {
            writeBuffer()
            addToBuffer(byte)
        } else {
            buffer = Pair(buffer!!.first + 1, buffer!!.second)
        }
    }

    private fun isNeedToWriteBuffer(byte: Int): Boolean {
        return byte != buffer!!.second || buffer!!.first >= Constants.MAX_BLOCK_SIZE
    }

    override fun flush() {
        writeBuffer()
        stream.flush()
    }

    private fun writeBuffer() {
        stream.write(buffer!!.first)
        stream.write(buffer!!.second)
        buffer = null
    }

    private fun addToBuffer(byte: Int) {
        buffer = Pair(1, byte)
    }
}