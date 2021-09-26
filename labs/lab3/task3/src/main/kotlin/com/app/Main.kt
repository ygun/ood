package com.app

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

fun main(args: Array<String>) {
    val decorators = createDecorators(args)
    val inputStream = decorators.first
    val outputStream = decorators.second

    inputStream.copyTo(outputStream)
    outputStream.flush()
}

private fun createDecorators(args: Array<String>): Pair<InputStream, OutputStream> {
    val inputStream: InputStream = FileInputStream(args[args.size - 2])
    val outputStream: OutputStream = FileOutputStream(args[args.size - 1])
    return StreamDecoratorsFabric(
        args.copyOfRange(0, args.size - 2),
        inputStream,
        outputStream
    ).build()
}
