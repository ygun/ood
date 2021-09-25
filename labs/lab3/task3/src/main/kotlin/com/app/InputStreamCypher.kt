package com.app

import java.io.InputStream
import kotlin.random.Random

class InputStreamCypher(
    private val stream: InputStream,
    private val key: Int
) : InputStream() {

    override fun read(): Int {
        return getCypherTable(key.toLong()).getValue(stream.read())
    }

    companion object {
        fun getCypherTable(key: Long): Map<Int, Int> {
            return (0..255)
                .shuffled(Random(key))
                .mapIndexed { index, it -> Pair(index, it) }
                .toMap()
        }
    }
}
