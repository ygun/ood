package com.app.compress

import java.io.OutputStream
import java.util.*

class CompressOutputStream(
    private val stream: OutputStream
) : OutputStream() {

    private val buffer: MutableList<Int> = LinkedList()
    
    companion object {
        private const val INDEX_OF_REPEAT = 0
        private const val INDEX_OF_CURRENT_BITE = 1
    }
    
    override fun write(byte: Int) {
        if (buffer.isEmpty()) {
            addToBuffer(byte)
        } else if (isNeedToWriteBuffer(byte)) {
            writeBuffer()
            addToBuffer(byte)
        } else {
            buffer[INDEX_OF_REPEAT]++
        }
    }

    private fun isNeedToWriteBuffer(byte: Int): Boolean {
        return byte != buffer[INDEX_OF_CURRENT_BITE] || buffer[INDEX_OF_REPEAT] > Constants.MAX_BLOCK_SIZE
    }

    override fun flush() {
        writeBuffer()
        stream.flush()
    }

    private fun writeBuffer() {
        buffer.forEach {
            stream.write(it)
        }
    }

    private fun addToBuffer(bite: Int) {
        buffer.addAll(0, mutableListOf(1, bite))
    }
}