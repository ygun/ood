package com.app

import com.app.behavior.fly.flightCountingWrapper
import com.app.behavior.fly.flyRocketPower
import com.app.duck.DecoyDuck
import com.app.duck.MallardDuck
import com.app.duck.RedHeadDuck

fun main() {
    println("< Mallard Duck >")
    val mallard = MallardDuck()
    mallard.fly()
    mallard.fly()
    mallard.fly()
    mallard.fly = {}
    println("< change fly behavior >")
    mallard.fly()
    mallard.fly()

    println()

    println("< Decoy Duck >")
    val decoy = DecoyDuck()
    decoy.fly()
    decoy.fly = flightCountingWrapper(::flyRocketPower)
    println("< change fly behavior >")
    decoy.fly()
    decoy.fly()

    println()

    println("< RedHead Duck >")
    val readHead = RedHeadDuck()
    readHead.fly()
    readHead.fly()
    readHead.fly = flightCountingWrapper(::flyRocketPower)
    println("< change fly behavior >")
    readHead.fly()
    readHead.fly()
}