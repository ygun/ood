package com.app.component.coffee

import com.app.beverage.Beverage
import com.app.beverage.Portion
import com.app.beverage.Size

class DarkRoast(
    size: Size = Size.MIDDLE,
    portion: Portion = Portion.STANDARD
) : Beverage(size, portion) {

    override fun description(): String = "Dark Roast Coffee"

    override fun cost(): Double {
        return 0.99
    }
}