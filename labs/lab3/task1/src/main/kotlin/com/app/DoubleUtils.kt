package com.app

import kotlin.math.pow
import kotlin.math.roundToInt

object DoubleUtils {
    @JvmStatic
    fun roundDouble(num: Double, decimalPlaces: Int = 2) : Double {
        val numSignificantDigits = 10.0.pow(decimalPlaces.toDouble())
        return (num * numSignificantDigits).roundToInt() / numSignificantDigits
    }
}