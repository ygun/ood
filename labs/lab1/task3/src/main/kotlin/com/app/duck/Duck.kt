package com.app.duck

abstract class Duck(
    protected var fly: () -> Unit,
    protected var quack: () -> Unit,
    protected var dance: () -> Unit
) {
    abstract fun display()

    fun performFly() {
        return fly()
    }

    fun performQuack() {
        return quack()
    }

    fun performDance() {
        return dance()
    }

    fun swim() {
        println("All ducks float, even decoys!")
    }
}