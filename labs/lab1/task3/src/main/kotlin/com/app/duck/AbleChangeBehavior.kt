package com.app.duck

interface AbleChangeBehavior {
    fun changeFly(fly: () -> Unit)

    fun changeDance(dance: () -> Unit)

    fun changeQuack(quack: () -> Unit)
}