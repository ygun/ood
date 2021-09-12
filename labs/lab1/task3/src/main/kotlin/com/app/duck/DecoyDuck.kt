package com.app.duck

class DecoyDuck : Duck(
    fly = {},
    quack = {},
    dance = {}
) {
    override fun display() {
        println("I'm a decoy duck")
    }
}