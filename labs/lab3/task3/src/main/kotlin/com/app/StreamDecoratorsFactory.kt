package com.app

import com.app.compress.CompressOutputStream
import com.app.compress.DecompressInputStream
import com.app.cypher.DecryptInputStream
import com.app.cypher.EncryptOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.util.*

typealias StreamWithKey = Pair<Operation, Long?>

class StreamDecoratorsFactory(
    private val args: Array<String>,
    private var inputStream: InputStream,
    private var outputStream: OutputStream
) {

    fun build(): Pair<InputStream, OutputStream> {
        val argsQueue = toQueue(args)

        var inputStreams = mutableListOf<StreamWithKey>()
        var outputStreams = mutableListOf<StreamWithKey>()
        while (argsQueue.isNotEmpty()) {
            val decoratedStreams = generateStreamsOrder(argsQueue, inputStreams, outputStreams)
            inputStreams = decoratedStreams.first
            outputStreams = decoratedStreams.second
        }

        return Pair(
            buildInputStream(inputStreams, inputStream),
            buildOutputStream(outputStreams, outputStream)
        )
    }

    private fun buildInputStream(streams: MutableList<StreamWithKey>, stream: InputStream): InputStream {
        var resStream = stream
        streams.forEach { (operation, key) ->
            resStream = when (operation) {
                Operation.DECRYPT -> DecryptInputStream(resStream, key!!)
                Operation.DECOMPRESS -> DecompressInputStream(resStream)
                else -> (throw IllegalArgumentException("Not correct operation: $operation"))
            }
        }
        return resStream
    }

    private fun buildOutputStream(streams: MutableList<StreamWithKey>, stream: OutputStream): OutputStream {
        var resStream = stream
        streams.reversed().forEach { (operation, key) ->
            resStream = when (operation) {
                Operation.ENCRYPT -> EncryptOutputStream(resStream, key!!)
                Operation.COMPRESS -> CompressOutputStream(resStream)
                else -> (throw IllegalArgumentException("Not correct operation: $operation"))
            }
        }
        return resStream
    }

    private fun generateStreamsOrder(
        argsQueue: Queue<String>, inputSteams: MutableList<StreamWithKey>, outputSteams: MutableList<StreamWithKey>
    ): Pair<MutableList<StreamWithKey>, MutableList<StreamWithKey>> {
        when (val currOperation = argsQueue.poll()) {
            Operation.ENCRYPT.toString() -> {
                val key = argsQueue.poll().toLong()
                outputSteams.add(Pair(Operation.ENCRYPT, key))
            }
            Operation.DECRYPT.toString() -> {
                val key = argsQueue.poll().toLong()
                inputSteams.add(Pair(Operation.DECRYPT, key))
            }
            Operation.COMPRESS.toString() -> outputSteams.add(Pair(Operation.COMPRESS, null))
            Operation.DECOMPRESS.toString() -> inputSteams.add(Pair(Operation.DECOMPRESS, null))
            else -> throw IllegalArgumentException("Not correct operation: $currOperation")
        }
        return Pair(inputSteams, outputSteams)
    }
}

fun toQueue(array: Array<String>): Queue<String> {
    val queue = LinkedList<String>()
    array.forEach(queue::add)
    return queue
}
