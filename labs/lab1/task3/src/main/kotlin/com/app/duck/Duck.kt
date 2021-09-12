package com.app.duck

abstract class Duck(
    var fly: () -> Unit,
    var quack: () -> Unit,
    var dance: () -> Unit
) {
    abstract fun display()

    fun swim() {
        println("All ducks float, even decoys!")
    }
}