package com.app.component.coffee

import com.app.beverage.Beverage
import com.app.beverage.Portion
import com.app.beverage.Size

class Latte(
    size: Size = Size.MIDDLE,
    portion: Portion = Portion.STANDARD
) : Beverage(size, portion) {

    override fun description(): String = "$portion Latte"

    override fun cost(): Double = when(portion) {
        Portion.STANDARD -> 1.23
        Portion.DOUBLE -> 1.78
    }
}