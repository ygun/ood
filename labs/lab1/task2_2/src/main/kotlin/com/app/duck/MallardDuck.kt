package com.app.duck

import com.app.behavior.dance.DanceWaltz
import com.app.behavior.fly.FlyWithWings
import com.app.behavior.quack.Quack

class MallardDuck : Duck(
    flyBehavior = FlyWithWings(),
    quackBehavior = Quack(),
    danceBehavior = DanceWaltz()
) {
    override fun display() {
        println("I'm a real Mallard duck")
    }
}