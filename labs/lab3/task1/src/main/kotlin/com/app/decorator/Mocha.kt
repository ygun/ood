package com.app.decorator

import com.app.DoubleUtils
import com.app.beverage.Beverage
import com.app.beverage.Size

class Mocha(private val beverage: Beverage) : CondimentDecorator(beverage) {

    override var description: String = beverage.description + ", Mocha"

    override fun cost(): Double {
        val cost = beverage.cost()
        return DoubleUtils.roundDouble(
            when (beverage.size) {
                Size.SMALL -> cost + 0.20
                Size.MIDDLE -> cost + 0.25
                Size.BIG -> cost + 0.30
            }
        )
    }
}