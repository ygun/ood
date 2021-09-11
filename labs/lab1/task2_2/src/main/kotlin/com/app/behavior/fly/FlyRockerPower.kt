package com.app.behavior.fly

class FlyRockerPower : FlyBehavior {

    var numberOfFlights: Int = 0

    override fun fly() {
        println("I'm flying with by rocket power, count flight: ${++numberOfFlights}")
    }
}