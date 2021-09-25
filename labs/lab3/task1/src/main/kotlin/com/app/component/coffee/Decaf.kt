package com.app.component.coffee

import com.app.beverage.Beverage
import com.app.beverage.Size

class Decaf(size: Size = Size.MIDDLE) : Beverage(size) {

    override fun description(): String = "Decaf Coffee"

    override fun cost(): Double {
        return 1.05
    }
}