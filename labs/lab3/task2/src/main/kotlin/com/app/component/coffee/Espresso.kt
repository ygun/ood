package com.app.component.coffee

import com.app.beverage.Beverage
import com.app.beverage.Size

class Espresso(size: Size = Size.MIDDLE) : Beverage(size) {

    override fun description(): String = "Espresso"

    override fun cost(): Double = 1.99
}