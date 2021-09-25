package com.app.component.coffee

import com.app.beverage.Beverage
import com.app.beverage.Size

class Espresso(size: Size = Size.MIDDLE) : Beverage(size) {

    init {
        description = "Espresso"
    }

    override fun cost(): Double {
        return 1.99
    }
}