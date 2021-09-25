package com.app

import java.io.InputStream
import java.io.OutputStream


fun main(args: Array<String>) {
    println("Hello World!")

    println("Program arguments: ${args.joinToString()}")
}


fun parsArgs(args: Array<String>): Pair<InputStream, OutputStream>? {
    return null
}