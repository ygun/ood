package com.app.behavior.fly

class FlyCountingWrapper(
    var flyBehavior: FlyBehavior
) : FlyBehavior {

    var numberOfFlights: Int = 0

    override fun fly() {
        numberOfFlights++
        flyBehavior.fly()
        println("Count flight: $numberOfFlights")
    }
}