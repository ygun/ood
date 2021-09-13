package com.app.duck

class DecoyDuck : Duck(
    fly = {},
    quack = {},
    dance = {}
), AbleChangeBehavior {
    override fun display() {
        println("I'm a decoy duck")
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