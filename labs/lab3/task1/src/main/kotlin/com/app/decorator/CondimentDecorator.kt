package com.app.decorator

import com.app.beverage.Beverage
import com.app.beverage.Portion
import com.app.beverage.Size
import kotlin.math.roundToInt

abstract class CondimentDecorator(beverage: Beverage) : Beverage(beverage.size, beverage.portion) {
    abstract override var description: String
}