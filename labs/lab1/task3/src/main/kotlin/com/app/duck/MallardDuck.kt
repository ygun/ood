package com.app.duck

import com.app.behavior.dance.danceWaltz
import com.app.behavior.fly.flightCountingWrapper
import com.app.behavior.fly.flyWithWings
import com.app.behavior.quack.quack

class MallardDuck : Duck(
    fly = flightCountingWrapper(::flyWithWings),
    quack = ::quack,
    dance = ::danceWaltz
), AbleChangeBehavior {
    override fun display() {
        println("I'm a real Mallard duck")
    }

    override fun changeFly(fly: () -> Unit) {
        this.fly = fly
    }

    override fun changeDance(dance: () -> Unit) {
        this.dance = dance
    }

    override fun changeQuack(quack: () -> Unit) {
        this.quack = quack
    }
}