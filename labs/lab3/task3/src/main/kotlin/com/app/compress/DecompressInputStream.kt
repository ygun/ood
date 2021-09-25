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
            val currBite = stream.read()

            if (!isEOF(currBite)) {
                readBlock(numberOfRepeat, currBite)
            }
        }

        return when (val headOfQueue = biteQueue.poll()) {
            null -> -1
            else -> headOfQueue
        }
    }

    private fun readBlock(numberOfRepeat: Int, bite: Int) {
        repeat(numberOfRepeat) { biteQueue.add(bite) }
    }

    private fun isEOF(bite: Int) = bite == -1
}