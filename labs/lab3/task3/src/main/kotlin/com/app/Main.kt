package com.app

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

fun main(args: Array<String>) {
    try {
        val decorators = createDecorators(args)
        val inputStream = decorators.first
        val outputStream = decorators.second

        writeTo(inputStream, outputStream)
        outputStream.flush()
    } catch (e: Exception) {
        println(e.message)
    }
}

private fun writeTo(inputStream: InputStream, outputStream: OutputStream) {
    var currByte = 0
    while (currByte != -1) {
        currByte = inputStream.read()
        if (currByte != -1)
            outputStream.write(currByte)
    }
}

private fun createDecorators(args: Array<String>): Pair<InputStream, OutputStream> {
    if (args.size < 2) throw RuntimeException("Not correct count of arguments")
    val inputStream: InputStream = FileInputStream(args[args.size - 2])
    val outputStream: OutputStream = FileOutputStream(args[args.size - 1])
    return StreamDecoratorsFactory(
        args.copyOfRange(0, args.size - 2),
        inputStream,
        outputStream
    ).build()
}
