package com.app.duck

import com.app.behavior.dance.danceMinuet
import com.app.behavior.fly.flyWithWings
import com.app.behavior.quack.quack

class RedHeadDuck : Duck(
    fly = flyWithWings(),
    quack = ::quack,
    dance = ::danceMinuet
) {
    override fun display() {
        println("I'm a real Red Headed duck")
    }
}