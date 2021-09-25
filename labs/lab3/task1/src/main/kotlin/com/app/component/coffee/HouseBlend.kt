package com.app.component.coffee

import com.app.beverage.Beverage
import com.app.beverage.Size

class HouseBlend(size: Size = Size.MIDDLE) : Beverage(size) {

    override fun description(): String = "House Blend"

    override fun cost(): Double {
        return 0.89
    }
}