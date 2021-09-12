package com.app.behavior.fly

fun flyWithWings(): () -> Unit {
    var numberOfFlights: Int = 0
    return {
        println("I'm flying, count flight: ${++numberOfFlights}")
    }
}