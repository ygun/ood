package com.app.component.coffee

import com.app.beverage.Beverage
import com.app.beverage.Portion
import com.app.beverage.Size

class Cappuccino(
    size: Size = Size.MIDDLE,
    portion: Portion = Portion.STANDARD
) : Beverage(size, portion) {

    init {
        description = "$portion Cappuccino"
    }

    override fun cost(): Double {
        return when(portion) {
            Portion.STANDARD -> 1.10
            Portion.DOUBLE -> 1.65
        }
    }
}