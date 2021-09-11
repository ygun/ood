package com.app.duck

import com.app.behavior.fly.FlyBehavior
import com.app.behavior.quack.QuackBehavior

abstract class Duck(
    var flyBehavior: FlyBehavior,
    var quackBehavior: QuackBehavior
) {
    abstract fun display()

    fun performFly() {
        flyBehavior.fly()
    }

    fun performQuack() {
        quackBehavior.quack()
    }

    fun swim() {
        println("All ducks float, even decoys!")
    }
}