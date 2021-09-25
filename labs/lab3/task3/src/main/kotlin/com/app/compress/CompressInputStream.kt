package com.app.compress

import java.io.InputStream
import java.util.*

class CompressInputStream(
    private val stream: InputStream
) : InputStream() {

    private val biteQueue: Queue<Int> = LinkedList()
    private var lastBiteFromLastBlock = -1

    companion object {
        const val MAX_BLOCK_SIZE = 255
    }

    override fun read(): Int {
        if (biteQueue.isEmpty()) {
            val readBite = stream.read()
            if (!isEOF(readBite)) {
                readBlock(readBite)
            }
        }

        return when (val headOfQueue = biteQueue.poll()) {
            null -> -1
            else -> headOfQueue
        }
    }

    private fun readBlock(startBite: Int) {
        var currBite = startBite
        var prevBite = lastBiteFromLastBlock

        if (currBite != prevBite)
            pushNumRepeatAndBiteToQueue(1, prevBite)

        var numberOfRepeat = if (currBite == prevBite) 2 else 1

        while (isInRecurringSequence(numberOfRepeat, prevBite, currBite)) {
            prevBite = currBite
            numberOfRepeat++
            currBite = stream.read()
        }

        pushNumRepeatAndBiteToQueue(numberOfRepeat, prevBite)

        lastBiteFromLastBlock = currBite
    }

    private fun pushNumRepeatAndBiteToQueue(numberOfRepeat: Int, bite: Int) {
        biteQueue.add(numberOfRepeat)
        biteQueue.add(bite)
    }

    private fun isInRecurringSequence(numberOfRepeat: Int, prevBite: Int, currBite: Int): Boolean {
        return (numberOfRepeat != MAX_BLOCK_SIZE) && (prevBite != currBite)
    }

    private fun isEOF(bite: Int) = bite == -1
}