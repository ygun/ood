package com.app.behavior.fly

class FlyWithWings : FlyBehavior {
    override var numberOfFlights: Int = 0

    override fun fly() {
        numberOfFlights++
        println("I'm flying, flight number: $numberOfFlights")
    }
}