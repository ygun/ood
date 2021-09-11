package com.app.behavior.fly

class FlyRockerPower : FlyBehavior {
    override var numberOfFlights: Int = 0

    override fun fly() {
        numberOfFlights++
        println("I'm flying with by rocket power, flight number: $numberOfFlights")
    }
}