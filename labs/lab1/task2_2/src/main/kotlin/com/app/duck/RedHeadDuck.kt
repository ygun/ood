package com.app.duck

import com.app.behavior.dance.DanceMinuet
import com.app.behavior.fly.FlyWithWings
import com.app.behavior.quack.Quack

class RedHeadDuck : Duck(
    flyBehavior = FlyWithWings(),
    quackBehavior = Quack(),
    danceBehavior = DanceMinuet()
) {
    override fun display() {
        println("I'm a real Red Headed duck")
    }
}