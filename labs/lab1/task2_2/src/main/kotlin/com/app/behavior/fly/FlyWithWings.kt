package com.app.behavior.fly

class FlyWithWings : FlyBehavior {

    var numberOfFlights: Int = 0

    override fun fly() {
       println("I'm flying, count flight: ${++numberOfFlights}")
    }
}