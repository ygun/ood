package com.app.decorator

import com.app.DoubleUtils
import com.app.beverage.Beverage
import com.app.beverage.Size

class Whip(private val beverage: Beverage) : CondimentDecorator(beverage) {

    override fun description(): String = beverage.description() + ", Whip"

    override fun cost(): Double {
        val cost = beverage.cost()
        return DoubleUtils.roundDouble(
            when (beverage.size) {
                Size.SMALL -> cost + 0.10
                Size.MIDDLE -> cost + 0.15
                Size.BIG -> cost + 0.20
            }
        )
    }
}