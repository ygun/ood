package com.app.behavior.fly

class FlyNoWay() : FlyBehavior {
    override var numberOfFlights: Int = 0

    override fun fly() {
        println("I can’t fly on my paws")
    }
}