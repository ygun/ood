package com.app

import com.app.behavior.fly.flightCountingWrapper
import com.app.behavior.fly.flyRocketPower
import com.app.duck.DecoyDuck
import com.app.duck.MallardDuck
import com.app.duck.RedHeadDuck

fun main() {
    println("< Mallard Duck >")
    val mallard = MallardDuck()
    mallard.performFly()
    mallard.performFly()
    mallard.performFly()
    mallard.changeFly {}
    println("< change fly behavior mallard >")
    mallard.performFly()
    mallard.performFly()
    mallard.swim()
    mallard.performQuack()

    println()

    println("< Decoy Duck >")
    val decoy = DecoyDuck()
    decoy.performFly()
    decoy.changeFly(flightCountingWrapper(::flyRocketPower))
    println("< change fly behavior decoy >")
    decoy.performFly()
    decoy.performFly()
    decoy.swim()
    decoy.performDance()

    println()

    println("< RedHead Duck >")
    val readHead = RedHeadDuck()
    readHead.performFly()
    readHead.performFly()
    readHead.swim()
    readHead.performQuack()
}