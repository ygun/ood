package com.app.compress

import java.io.InputStream
import java.util.*

class DecompressInputStream(
    private val stream: InputStream
) : InputStream() {

    private val biteQueue: Queue<Int> = LinkedList()

    override fun read(): Int {
        if (biteQueue.isEmpty()) {
            val numberOfRepeat = stream.read()
            val currByte = stream.read()

            if (!isEOF(currByte)) {
                readBlock(numberOfRepeat, currByte)
            }
        }

        return when (val headOfQueue = biteQueue.poll()) {
            null -> -1
            else -> headOfQueue
        }
    }

    private fun readBlock(numberOfRepeat: Int, byte: Int) {
        repeat(numberOfRepeat) { biteQueue.add(byte) }
    }

    private fun isEOF(byte: Int) = byte == -1
}