package com.app

import java.io.OutputStream
import kotlin.random.Random

class OutputStreamCypher(
    private val stream: OutputStream,
    private val key: Int
) : OutputStream() {

    override fun write(b: Int) {
        stream.write(getCypherTable(key.toLong()).getValue(b))
    }

    companion object {
        fun getCypherTable(key: Long): Map<Int, Int> {
            return (0..255)
                .shuffled(Random(key))
                .mapIndexed { index, it -> Pair(it, index) }
                .toMap()
        }
    }
}
