package com.app.duck

import com.app.behavior.dance.DanceBehavior
import com.app.behavior.fly.FlyBehavior
import com.app.behavior.quack.QuackBehavior

abstract class Duck(
    var flyBehavior: FlyBehavior,
    var quackBehavior: QuackBehavior,
    var danceBehavior: DanceBehavior
) {
    abstract fun display()

    fun performFly() {
        flyBehavior.fly()
    }

    fun performQuack() {
        quackBehavior.quack()
    }

    fun performDance() {
        danceBehavior.dance()
    }

    fun swim() {
        println("All ducks float, even decoys!")
    }
}