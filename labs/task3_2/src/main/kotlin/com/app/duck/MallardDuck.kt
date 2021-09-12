package com.app.duck

import com.app.behavior.dance.danceWaltz
import com.app.behavior.fly.flyWithWings
import com.app.behavior.quack.quack

class MallardDuck : Duck(
    fly = flyWithWings(),
    quack = ::quack,
    dance = ::danceWaltz
) {
    override fun display() {
        println("I'm a real Mallard duck")
    }
}