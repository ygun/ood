package com.app

import java.io.InputStream
import java.util.zip.DeflaterOutputStream
import kotlin.random.Random

class RLESteam(
    private val stream: InputStream,
    private val key: Int
) : InputStream() {

    override fun read(): Int {
        return getCypherTable(key.toLong()).getValue(stream.read())
    }

    fun encode(input: String): String {
        return input.replace(Regex("(.)\\1*")) {
            String.format("%d%s", it.value.length, it.groupValues[1])
        }
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
