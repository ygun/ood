package com.app.decorator

import com.app.DoubleUtils
import com.app.beverage.Beverage
import com.app.beverage.Size

class Cream(private val beverage: Beverage) : CondimentDecorator(beverage) {

    override fun description(): String = beverage.description() + ", Cream"

    override fun cost(): Double {
        val cost = beverage.cost()
        return DoubleUtils.roundDouble(
            when (beverage.size) {
                Size.SMALL -> cost + 0.34
                Size.MIDDLE -> cost + 0.40
                Size.BIG -> cost + 0.45
            }
        )
    }
}