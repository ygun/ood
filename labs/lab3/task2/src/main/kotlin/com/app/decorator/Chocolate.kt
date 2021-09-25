package com.app.decorator

import com.app.DoubleUtils
import com.app.beverage.Beverage

class Chocolate(
    private val beverage: Beverage,
    private val numberOfSquares: Int = 1
) : CondimentDecorator(beverage) {

    init {
        require(numberOfSquares in 1..5) {
            "Relies on valid numberOfSquares in 1..5"
        }
    }

    override fun description(): String = when(numberOfSquares) {
        1 -> beverage.description() + ", Chocolate"
        else -> beverage.description() + ", $numberOfSquares Chocolate Squares"
    }

    override fun cost(): Double {
        return DoubleUtils.roundDouble(beverage.cost() + 0.14 * numberOfSquares)
    }
}