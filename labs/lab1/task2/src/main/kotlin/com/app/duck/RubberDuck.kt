package com.app.duck

import com.app.behavior.dance.DanceStay
import com.app.behavior.fly.FlyCountingWrapper
import com.app.behavior.fly.FlyNoWay
import com.app.behavior.quack.Squeak

class RubberDuck : Duck(
    flyBehavior = FlyNoWay(),
    quackBehavior = Squeak(),
    danceBehavior = DanceStay()
) {
    override fun display() {
        println("I'm a rubber duck")
    }
}