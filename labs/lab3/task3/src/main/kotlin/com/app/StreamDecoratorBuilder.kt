package com.app

import com.app.compress.CompressOutputStream
import com.app.compress.DecompressInputStream
import com.app.cypher.DecryptInputStream
import com.app.cypher.EncryptInputStream
import java.io.Closeable
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class StreamDecoratorBuilder(
    private val args: Array<String>,
    private var inputStream: InputStream,
    private var outputStream: OutputStream
) {

    fun build(): Pair<InputStream, OutputStream> {
        val argsQueue = toQueue(args)

        while (argsQueue.isNotEmpty()) {
            when (val tempStream = defineDecorator(argsQueue, inputStream, outputStream)) {
                is InputStream -> inputStream = tempStream
                is OutputStream -> outputStream = tempStream
            }
        }
        return Pair(inputStream, outputStream)
    }

    private fun defineDecorator(
        argsQueue: Queue<String>,
        inputSteam: InputStream,
        outputSteam: OutputStream
    ): Closeable {
        return when (val currOperation = argsQueue.poll()) {
            Operation.ENCRYPT.name -> {
                val key = argsQueue.poll().toLong()
                EncryptInputStream(outputSteam, key)
            }
            Operation.DECRYPT.name -> {
                val key = argsQueue.poll().toLong()
                DecryptInputStream(inputSteam, key)
            }
            Operation.COMPRESS.name -> CompressOutputStream(outputSteam)
            Operation.DECOMPRESS.name -> DecompressInputStream(inputSteam)
            else -> throw IllegalArgumentException("Not correct operation: $currOperation")
        }
    }

}

fun toQueue(array: Array<String>): Queue<String> {
    val queue = LinkedList<String>()
    array.forEach { queue.add(it) }
    return queue
}
