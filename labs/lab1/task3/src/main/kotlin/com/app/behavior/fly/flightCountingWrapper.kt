package com.app.behavior.fly

fun flightCountingWrapper(flyFun: () -> Unit): () -> Unit {
    var numberOfFlights: Int = 0

    return {
        flyFun()
        println("Count flight: ${++numberOfFlights}")
    }
}