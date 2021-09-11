package com.app.duck

import com.app.behavior.dance.DanceStay
import com.app.behavior.fly.FlyNoWay
import com.app.behavior.quack.MuteQuack

class DecoyDuck : Duck(
    flyBehavior = FlyNoWay(),
    quackBehavior = MuteQuack(),
    danceBehavior = DanceStay()
) {
    override fun display() {
        println("I'm a decoy duck")
    }
}