package com.app.duck

import com.app.behavior.quack.squeak

class RubberDuck : Duck(
    fly = {},
    quack = ::squeak,
    dance = {}
) {
    override fun display() {
        println("I'm a rubber duck")
    }
}