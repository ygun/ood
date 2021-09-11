package com.app.duck

import com.app.behavior.fly.FlyNoWay
import com.app.behavior.quack.Squeak

class RubberDuck : Duck(
    flyBehavior = FlyNoWay(),
    quackBehavior = Squeak()
) {
    override fun display() {
        println("I'm a rubber duck")
    }
}