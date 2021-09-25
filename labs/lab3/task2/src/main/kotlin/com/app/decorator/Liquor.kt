package com.app.decorator

import com.app.DoubleUtils
import com.app.beverage.Beverage

class Liquor(
    private val beverage: Beverage,
    private val liquorType: LiquorType = LiquorType.NUT
) : CondimentDecorator(beverage) {

    enum class LiquorType {
        NUT {
            override fun toString() = "Nut"
        },
        CHOCOLATE {
            override fun toString() = "Chocolate"
        }
    }

    override fun description(): String = beverage.description() + ", $liquorType Liquor"

    override fun cost(): Double {
        return DoubleUtils.roundDouble(beverage.cost() + 0.69)
    }
}