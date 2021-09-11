package com.app

import com.app.behavior.fly.FlyNoWay
import com.app.behavior.fly.FlyRockerPower
import com.app.duck.DecoyDuck
import com.app.duck.MallardDuck
import com.app.duck.RedHeadDuck

fun main() {
    println("< Mallard Duck >")
    val mallard = MallardDuck()
    mallard.performFly()
    mallard.performFly()
    mallard.performFly()
    mallard.flyBehavior = FlyNoWay()
    println("< change fly behavior >")
    mallard.performFly()
    mallard.performFly()

    println()

    println("< Decoy Duck >")
    val decoy = DecoyDuck()
    decoy.performFly()
    decoy.flyBehavior = FlyRockerPower()
    println("< change fly behavior >")
    decoy.performFly()
    decoy.performFly()

    println()

    println("< RedHead Duck >")
    val readHead = RedHeadDuck()
    readHead.performFly()
    readHead.performFly()
    readHead.flyBehavior = FlyRockerPower()
    println("< change fly behavior >")
    readHead.performFly()
    readHead.performFly()

}