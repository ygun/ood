package com.app.behavior.fly

fun flyRocketPower(): () -> Unit {
    var numberOfFlights: Int = 0
    return {
        println("I'm flying with by rocket power, count flight: ${++numberOfFlights}")
    }
}