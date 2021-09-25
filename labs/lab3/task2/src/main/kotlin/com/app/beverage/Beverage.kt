package com.app.beverage

abstract class Beverage(
    var size: Size = Size.MIDDLE,
    var portion: Portion = Portion.STANDARD
) {

    open var description = "Unknown beverage"

    abstract fun cost(): Double

    override fun toString(): String {
        return "$description;" +
                " cost = ${cost()};" +
                " size = $size;" +
                " portion = $portion"
    }
}