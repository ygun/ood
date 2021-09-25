package com.app.component

import com.app.beverage.Beverage
import com.app.beverage.Portion
import com.app.beverage.Size

class Milkshake(
    size: Size = Size.MIDDLE,
    portion: Portion = Portion.STANDARD
) : Beverage(size, portion) {

    override fun description(): String = "$portion Milkshake"

    override fun cost(): Double {
        return when(size) {
            Size.SMALL -> 0.69
            Size.MIDDLE -> 0.82
            Size.BIG -> 1.10
        }
    }
}